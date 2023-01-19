package com.sofka.shop.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.Shop;
import com.sofka.shop.commands.DeleteProduct;
import com.sofka.shop.entities.Product;
import com.sofka.shop.gateway.ShopDomainEventRepository;
import com.sofka.shop.generic.UseCaseForCommand;
import com.sofka.shop.values.ShopID;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoArgsConstructor
public class DeleteProductUseCase extends UseCaseForCommand<DeleteProduct> {

    // + ------------------------         Utils         ------------------------ + //

    private ShopDomainEventRepository repository;

    // + ------------------------      Constructor       ------------------------ + //

    public DeleteProductUseCase(ShopDomainEventRepository repository) {
        this.repository = repository;
    }

    // + ------------------------    Command actions     ------------------------ + //

    @Override
    public Flux<DomainEvent> apply(Mono<DeleteProduct> input) {
        return input.flatMapMany(command -> repository
            .getEventBy(command.getShopID())
            .collectList()
            .flatMapIterable(events->{
                var shop = Shop.from(ShopID.of(command.getShopID()), events);
                shop.deleteProduct(command.getShopID(), command.getProductId());
                return shop.getUncommittedChanges();
            })
        );
    }

}
