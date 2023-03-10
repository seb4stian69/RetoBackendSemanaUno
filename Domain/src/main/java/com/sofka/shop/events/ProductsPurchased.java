package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.values.CName;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter/* */@Setter
public class ProductsPurchased extends DomainEvent {

    private String shopID;
    private Instant date;
    private String idType;
    private String id;
    private CName clientName;
    private Map<String, Integer> products = new HashMap<>();

    public ProductsPurchased(String idType, String id, CName clientName, Map<String, Integer> products){
        super("shop.productbuy");
        this.date = Instant.now();
        this.idType = idType;
        this.id = id;
        this.clientName = clientName;
        this.products = products;
    }

}
