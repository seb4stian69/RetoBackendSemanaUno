package com.sofka.shop;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.shop.events.*;
import com.sofka.shop.utils.EncapsulateProduct;

import java.util.concurrent.atomic.AtomicInteger;

public class ShopChange extends EventChange {

    public ShopChange(Shop shop) {

        apply((ProductRegistered event) ->
            shop.products.add( event.getProduct() )
        );

        apply((ProductEdited event) -> shop.utils.findProductById(event.getProductID(),shop.products)
            .updateClass( EncapsulateProduct.productEditedNeeded(event) )
        );

        apply((ProductDeleted event) -> shop.products.remove(
            shop.utils.findProductById( event.getProductId(), shop.products )
        ));

        apply((ProductPurchased event) -> {

            shop.clientID = shop.utils.joinUUID(event.getIdType(), event.getId());
            shop.clientName = shop.utils.joinClientName(event.getClientName().value().name(), event.getClientName().value().lastName());

            AtomicInteger total = new AtomicInteger();

            event.getProducts().forEach( (idProduct, quantity) -> {

                shop.utils.detectedProblems(idProduct, quantity, shop.products);

                shop.utils.subtractProduct(idProduct, shop.products, quantity);
                total.addAndGet(shop.utils.findProductById(idProduct,shop.products).getPrice().value() * quantity);
                shop.productsPurchased.add(shop.utils.findProductById(idProduct,shop.products).getName().value());

            });

            shop.total = total.get();

        });

    }

}
