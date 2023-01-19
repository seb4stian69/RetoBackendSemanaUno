package com.sofka.shop.gateway;

import co.com.sofka.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShopDomainEventRepository {
    Flux<DomainEvent> getEventBy(String id);
    Mono<Boolean> exist(String id);
}
