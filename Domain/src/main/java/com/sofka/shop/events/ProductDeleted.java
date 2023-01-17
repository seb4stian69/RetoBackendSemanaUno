package com.sofka.shop.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.values.ProductID;
import com.sofka.shop.values.ShopID;
import lombok.Getter;
import lombok.Setter;

@Getter/* */@Setter
public class ProductDeleted extends DomainEvent {

    private ShopID shopID;
    private ProductID productId;

    public ProductDeleted(ShopID shopID, ProductID productId) {
        super("shop.productDeleted");
        this.shopID = shopID;
        this.productId = productId;
    }

}
