package com.sofka.shop;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.shop.events.*;
import com.sofka.shop.utils.EncapsulateProduct;
import com.sofka.shop.values.IDProduct;

import java.util.concurrent.atomic.AtomicInteger;

public class ShopChange extends EventChange {

    public ShopChange(Shop shop) {

        apply((ShopCreated event)-> shop.products = event.getProducts() );

        apply((ProductRegistered event) -> shop.products.add( event.getProduct() ) );

        apply((ProductEdited event) -> shop.utils.findProductById(event.getProductID(),shop.products)
            .updateClass( EncapsulateProduct.productEditedNeeded(event) )
        );

        apply((ProductDeleted event) -> shop.products.remove(
            shop.utils.findProductById( event.getProductId(), shop.products )
        ));

        apply((ProductsPurchased event) -> {

            shop.clientID = shop.utils.joinUUID(event.getIdType(), event.getId());
            shop.clientName = shop.utils.joinClientName(event.getClientName().value().name(), event.getClientName().value().lastName());

            AtomicInteger total = new AtomicInteger();

            event.getProducts().forEach( (idProduct, quantity) -> {

                shop.utils.detectedProblems(IDProduct.of(idProduct), quantity, shop.products);

                shop.utils.subtractProduct(IDProduct.of(idProduct), shop.products, quantity);
                total.addAndGet(shop.utils.findProductById(IDProduct.of(idProduct),shop.products).getPrice().value() * quantity);
                shop.productsPurchased.add(shop.utils.findProductById(IDProduct.of(idProduct),shop.products).getName().value());

            });

            shop.total = total.get();

        });

    }

}
