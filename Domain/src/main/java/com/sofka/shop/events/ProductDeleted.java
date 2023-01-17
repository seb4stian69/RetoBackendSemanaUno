package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class ProductDeleted extends DomainEvent {

    private String productId;

    public ProductDeleted(){
        super("shop.productDeleted");
    }

    public ProductDeleted(String productId) {
        super("shop.productDeleted");
        this.productId = productId;
    }

}
