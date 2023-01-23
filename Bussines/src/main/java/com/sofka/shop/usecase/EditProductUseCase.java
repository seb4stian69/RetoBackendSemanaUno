package com.sofka.shop.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.Shop;
import com.sofka.shop.commands.EditProduct;
import com.sofka.shop.gateway.ShopDomainEventRepository;
import com.sofka.shop.generic.UseCaseForCommand;
import com.sofka.shop.values.ShopID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EditProductUseCase extends UseCaseForCommand<EditProduct> {

    // + ------------------------         Utils         ------------------------ + //

    private ShopDomainEventRepository repository;

    // + ------------------------      Constructor       ------------------------ + //

    public EditProductUseCase(ShopDomainEventRepository repository) {
        this.repository = repository;
    }

    // + ------------------------    Command actions     ------------------------ + //

    @Override
    public Flux<DomainEvent> apply(Mono<EditProduct> input) {

        return input.flatMapMany(command->repository
            .getEventBy(command.getShopID())
            .collectList()
            .flatMapIterable(events->{

                var shop = Shop.from(ShopID.of(command.getShopID()), events);

                var shopID = command.getShopID();
                var productID = command.getProductID();
                var name = command.getName();
                var inINventory = command.getInINventory();
                var enabled = command.getEnabled();
                var max = command.getMax();
                var min = command.getMin();
                var price = command.getPrice();

                shop.editProduct(shopID,productID,name,inINventory,enabled,max,min,price);

                return shop.getUncommittedChanges();

            })
        );

    }

}
