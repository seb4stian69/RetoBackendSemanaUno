package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.entities.Product;
import com.sofka.shop.values.*;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class ProductRegistered extends DomainEvent {

    private final ShopID shopID;
    private final Product product;

    public ProductRegistered(ShopID shopID, Product product){
        super("shop.productregistered");
        this.shopID = shopID;
        this.product = product;
    }

}
