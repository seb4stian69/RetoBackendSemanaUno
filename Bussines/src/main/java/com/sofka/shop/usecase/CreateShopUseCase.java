package com.sofka.shop.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.Shop;
import com.sofka.shop.commands.CreateShop;
import com.sofka.shop.gateway.ListProductService;
import com.sofka.shop.generic.UseCaseForCommand;
import com.sofka.shop.values.ShopID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreateShopUseCase extends UseCaseForCommand<CreateShop> {

    // + ------------------------         Utils         ------------------------ + //

    private final ListProductService listProductService;

    // + ------------------------      Constructor       ------------------------ + //

    public CreateShopUseCase(ListProductService listProductService) {
        this.listProductService = listProductService;
    }

    // + ------------------------    Command actions     ------------------------ + //

    @Override
    public Flux<DomainEvent> apply(Mono<CreateShop> input) {
        return listProductService.obtenerProductos().collectList()
                .flatMapMany(products -> input.flatMapIterable(command->{
                    var shop = new Shop(ShopID.of(command.getShopID()), products);
                    return shop.getUncommittedChanges();
                }));
    }

}
