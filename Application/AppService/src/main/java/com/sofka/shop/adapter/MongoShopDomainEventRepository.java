package com.sofka.shop.adapter;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.gateway.ShopDomainEventRepository;
import com.sofka.shop.generic.EventStoreRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MongoShopDomainEventRepository implements ShopDomainEventRepository {

    private final EventStoreRepository repository;

    public MongoShopDomainEventRepository(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> getEventBy(String id) {
        return repository.getEventsBy("shop", id);
    }
    @Override
    public Mono<Boolean> exist(String id) {
        return null;
    }

}
