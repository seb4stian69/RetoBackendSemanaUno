package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.values.CName;
import com.sofka.shop.values.PName;
import com.sofka.shop.values.Price;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter/* */@Setter
public class ProductPurchased extends DomainEvent {

    private Instant date;
    private String idType;
    private String id;
    private CName clientName;
    private Map<PName, Price> products;

    public ProductPurchased(String idType, String id, CName clientName,Map<PName, Price> products){
        super("shop.productPurchased");
        this.date = Instant.now();
        this.idType = idType;
        this.id = id;
        this.clientName = clientName;
        this.products = products;
    }

}
