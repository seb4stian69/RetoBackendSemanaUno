package com.sofka.shop.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.shop.values.*;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class Product extends Entity<IDProduct> {

    private final IDProduct productID;
    private final PName name;
    private InInventory inInventory;
    private Enable isEnabled;
    private final Max max;
    private final Min min;
    private final Price price;

    public Product(IDProduct id, PName name, InInventory inInventory, Enable isEnabled, Max max, Min min, Price price) {
        super(id);
        this.productID = id;
        this.name = name;
        this.inInventory = inInventory;
        this.isEnabled = isEnabled;
        this.max = max;
        this.min = min;
        this.price = price;
    }

}
