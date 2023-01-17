package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter/* */@Setter
public class ShopCreated extends DomainEvent {

    private Map<String, Integer> products;

    public ShopCreated() {
        super("shop.createdShop");
    }

    public ShopCreated(Map<String, Integer> products) {
        super("shop.createdShop");
        this.products = products;
    }

}
