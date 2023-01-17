package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.values.*;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class ProductRegistered extends DomainEvent {

    private PName name;
    private InInventory inINventory;
    private Enable enabled;
    private Max max;
    private Min min;

    public ProductRegistered(){
        super("shop.productregistered");
    }

    public ProductRegistered(PName name, InInventory inINventory, Enable enabled, Max max, Min min){
        super("shop.productregistered");
        this.name = name;
        this.inINventory = inINventory;
        this.enabled = enabled;
        this.max = max;
        this.min = min;
    }

}
