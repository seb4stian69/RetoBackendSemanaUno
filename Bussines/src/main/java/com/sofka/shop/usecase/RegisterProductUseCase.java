package com.sofka.shop.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.Shop;
import com.sofka.shop.commands.RegisterProduct;
import com.sofka.shop.entities.Product;
import com.sofka.shop.gateway.ShopDomainEventRepository;
import com.sofka.shop.generic.UseCaseForCommand;
import com.sofka.shop.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RegisterProductUseCase extends UseCaseForCommand<RegisterProduct> {

    // + ------------------------         Utils         ------------------------ + //

    private final ShopDomainEventRepository repository;

    // + ------------------------      Constructor       ------------------------ + //

    public RegisterProductUseCase(ShopDomainEventRepository repository) {
        this.repository = repository;
    }

    // + ------------------------    Command actions     ------------------------ + //

    @Override
    public Flux<DomainEvent> apply(Mono<RegisterProduct> input) {

        return input.flatMapMany(command->repository
                .getEventBy(command.getShopID())
                .collectList()
                .flatMapIterable(events->{

                    var shop = Shop.from(ShopID.of(command.getShopID()), events);

                    IDProduct productID = IDProduct.of(command.getProductID());
                    PName name = PName.of(command.getName());
                    InInventory inInventory = InInventory.of(command.getInINventory());
                    Enable isEnabled = Enable.of(command.getEnabled());
                    Max max = Max.of(command.getMax());
                    Min min = Min.of(command.getMin());
                    Price price = Price.of(command.getPrice());

                    var productSend = new Product(productID,name,inInventory,isEnabled,max,min,price);

                    shop.registerProduct(ShopID.of(command.getShopID()), productSend);

                    return shop.getUncommittedChanges();

                })
        );

    }

}
