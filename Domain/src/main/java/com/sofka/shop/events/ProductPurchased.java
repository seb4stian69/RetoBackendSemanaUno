package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.values.CName;
import com.sofka.shop.values.IDProduct;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter/* */@Setter
public class ProductPurchased extends DomainEvent {

    private String shopID;
    private Instant date;
    private String idType;
    private String id;
    private CName clientName;
    private Map<IDProduct, Integer> products = new HashMap<IDProduct, Integer>();

    public ProductPurchased(String idType, String id, CName clientName,Map<IDProduct, Integer> products){
        super("shop.productPurchased");
        this.date = Instant.now();
        this.idType = idType;
        this.id = id;
        this.clientName = clientName;
        this.products = products;
    }

}
