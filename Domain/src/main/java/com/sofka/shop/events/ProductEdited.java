package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.values.*;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class ProductEdited extends DomainEvent {

    private ShopID shopID;
    private IDProduct productID;
    private PName name;
    private InInventory inINventory;
    private Enable enabled;
    private Max max;
    private Min min;
    private Price price;

    public ProductEdited(ShopID shopID, IDProduct productID, PName name, InInventory inINventory, Enable enabled, Max max, Min min, Price price) {
        super("shop.productEdited");
        this.shopID = shopID;
        this.productID = productID;
        this.name = name;
        this.inINventory = inINventory;
        this.enabled = enabled;
        this.max = max;
        this.min = min;
        this.price = price;
    }

}
