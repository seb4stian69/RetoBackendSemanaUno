package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.entities.Product;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter/* */@Setter
public class ShopCreated extends DomainEvent {

    private Set<Product> products;

    public ShopCreated() {
        super("shop.createdShop");
    }

    public ShopCreated(Set<Product> products) {
        super("shop.createdShop");
        this.products = products;
    }

}
