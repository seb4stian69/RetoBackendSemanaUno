package com.sofka.shop.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.shop.utils.EncapsulateProduct;
import com.sofka.shop.values.*;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class Product extends Entity<IDProduct> {

    private final IDProduct productID;
    private PName name;
    private InInventory inInventory;
    private Enable isEnabled;
    private Max max;
    private Min min;
    private Price price;

    public Product(IDProduct productID, PName name, InInventory inInventory, Enable isEnabled, Max max, Min min, Price price) {

        super(productID);
        this.productID = productID;
        this.name = name;
        this.inInventory = inInventory;
        this.isEnabled = isEnabled;
        this.max = max;
        this.min = min;
        this.price = price;

    }

    public void updateClass(EncapsulateProduct product){
        this.name = product.getProductName();
        this.inInventory = product.getInInventory();
        this.isEnabled = product.getIsEnabled();
        this.max = product.getMax();
        this.min = product.getMin();
        this.price = product.getPrice();
    }

}
