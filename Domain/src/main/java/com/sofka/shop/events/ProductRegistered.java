package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class ProductRegistered extends DomainEvent {

    private String name;
    private Integer inINventory;
    private Boolean enabled;
    private Integer max;
    private Integer min;

    public ProductRegistered(){
        super("shop.productregistered");
    }

    public ProductRegistered(String name, Integer inINventory, Boolean enabled, Integer max, Integer min){
        super("shop.productregistered");
        this.name = name;
        this.inINventory = inINventory;
        this.enabled = enabled;
        this.max = max;
        this.min = min;
    }

}
