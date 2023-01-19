package com.sofka.shop.utils;

import com.sofka.shop.events.ProductEdited;
import com.sofka.shop.values.*;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class EncapsulateProduct {
    
    private PName productName;
    private InInventory inInventory;
    private Enable isEnabled;
    private Max max;
    private Min min;
    private Price price;

    public EncapsulateProduct(ShopID shopID, IDProduct productID, PName productName, InInventory inInventory, Enable isEnabled, Max max, Min min, Price price) {
        this.productName = productName;
        this.inInventory = inInventory;
        this.isEnabled = isEnabled;
        this.max = max;
        this.min = min;
        this.price = price;
    }

    public static EncapsulateProduct productEditedNeeded(ProductEdited product){

        return new EncapsulateProduct(
                product.getShopID(),
                product.getProductID(),
                product.getName(),
                product.getInINventory(),
                product.getEnabled(),
                product.getMax(),
                product.getMin(),
                product.getPrice()
        );

    }


}
