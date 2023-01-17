package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.values.PName;
import com.sofka.shop.values.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter/* */@Setter
public class ShopCreated extends DomainEvent {

    private Map<PName, Price> products;

    public ShopCreated() {
        super("shop.createdShop");
    }

    public ShopCreated(Map<PName, Price> products) {
        super("shop.createdShop");
        this.products = products;
    }

}
