package com.sofka.shop.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.Shop;
import com.sofka.shop.commands.BuyProduct;
import com.sofka.shop.gateway.ShopDomainEventRepository;
import com.sofka.shop.generic.UseCaseForCommand;
import com.sofka.shop.utils.ShopUtils;
import com.sofka.shop.values.CName;
import com.sofka.shop.values.IDProduct;
import com.sofka.shop.values.ShopID;
import com.sofka.shop.values.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BuyProductUseCase extends UseCaseForCommand<BuyProduct> {

    private final Logger log = Logger.getLogger("BuyProductUseCase.class");

    // + ------------------------         Utils         ------------------------ + //

    private ShopDomainEventRepository repository;
    private ShopUtils utils;

    // + ------------------------      Constructor       ------------------------ + //

    public BuyProductUseCase(ShopDomainEventRepository repository) {
        this.repository = repository;
    }

    // + ------------------------    Command actions     ------------------------ + //

    @Override
    public Flux<DomainEvent> apply(Mono<BuyProduct> input) {

        return input.flatMapMany(command->repository

                .getEventBy(command.getShopID())
                .collectList()
                .flatMapIterable(events->{

                    var shop = Shop.from(ShopID.of(command.getShopID()),events);

                    var uuid = command.getIdType()+"-"+command.getIdClient();
                    var completeName = command.getClientName().split(" ");

                    shop.buyProduct(
                        UUID.of(uuid),
                        CName.of(completeName[0],completeName[1]),
                        command.getProducts()
                    );

                    return shop.getUncommittedChanges();

                })
        );

    }

}
