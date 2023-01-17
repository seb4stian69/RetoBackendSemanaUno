package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class ProductListed extends DomainEvent {

    private String productId;

    public ProductListed(){
        super("shop.produclisted");
    }

    public ProductListed(String productId){
        super("shop.produclisted");
        this.productId = productId;
    }

}
