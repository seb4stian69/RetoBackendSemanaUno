package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class ProductEdited extends DomainEvent {

    private String name;
    private Integer inINventory;
    private Boolean enabled;
    private Integer max;
    private Integer min;

    public ProductEdited(){
        super("shop.productEdited");
    }

    public ProductEdited(String name, Integer inINventory, Boolean enabled, Integer max, Integer min){
        super("shop.productEdited");
        this.name = name;
        this.inINventory = inINventory;
        this.enabled = enabled;
        this.max = max;
        this.min = min;
    }

}
