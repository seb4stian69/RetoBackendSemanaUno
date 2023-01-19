package com.sofka.shop.Adapter;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.gateway.ShopDomainEventRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MongoShopDomainEventRepository implements ShopDomainEventRepository {



    @Override
    public Flux<DomainEvent> getEventBy(String id) {
        return null;
    }

    @Override
    public Mono<Boolean> exist(String id) {
        return null;
    }
}
