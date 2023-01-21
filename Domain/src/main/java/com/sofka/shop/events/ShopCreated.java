package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.entities.Product;
import com.sofka.shop.values.ShopID;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter/* */@Setter
public class ShopCreated extends DomainEvent {

    private ShopID shopID;
    private List<Product> products;

    public ShopCreated() {
        super("shop.createdShop");
    }

    public ShopCreated(List<Product> products) {
        super("shop.createdShop");
        this.products = products;
    }

}
