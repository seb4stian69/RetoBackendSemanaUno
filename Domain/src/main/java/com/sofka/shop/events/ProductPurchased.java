package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter/* */@Setter
public class ProductPurchased extends DomainEvent {

    private Instant date;
    private String idType;
    private String id;
    private String clientName;
    private Map<String, Integer> products;

    public ProductPurchased(){
        super("shop.productPurchased");
    }

    public ProductPurchased(Instant date, String idType, String id, String clientName,Map<String, Integer> products){
        super("shop.productPurchased");
        this.date = date;
        this.idType = idType;
        this.id = id;
        this.clientName = clientName;
        this.products = products;
    }

}
