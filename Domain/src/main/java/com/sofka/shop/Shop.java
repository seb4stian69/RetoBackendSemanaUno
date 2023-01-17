package com.sofka.shop;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.entities.Product;
import com.sofka.shop.events.*;
import com.sofka.shop.utils.ShopUtils;
import com.sofka.shop.values.*;
import com.sofka.shop.values.UUID;

import java.util.*;
import java.util.Map;

public class Shop extends AggregateEvent<ShopID> {

    // + ------------------------      Atributes      ------------------------ + //

    protected List<Product> products;

    // + ------------------------  Invoice Atributes  ------------------------ + //

    protected String clientID;
    protected String clientName;
    protected List<String> productsPurchased;
    protected Integer total;


    // + ------------------------     Constructor     ------------------------ + //

    public Shop(ShopID entityId) {
        super(entityId);
        subscribe(new ShopChange(this));
        appendChange(new ShopCreated(products)).apply();
    }

    public static Shop from(ShopID entityId, List<DomainEvent> events){
        var shop = new Shop(entityId);
        events.forEach(shop::applyEvent);
        return shop;
    }

    // + ------------------------ Commands and Events ------------------------ + //

    public void registerProduct(ShopID shopID, Product product){

        Objects.requireNonNull(shopID, "ShopID is null and is necessary to register a product in one shop");
        Objects.requireNonNull(product, "Product is null and is necessary to register a product in a shop");

        appendChange(new ProductRegistered(shopID,product)).apply();

    }

    public void editProduct(String productID, String name, Integer inINventory, Boolean enabled, Integer max, Integer min, Integer price){

        Objects.requireNonNull(name, "Name is null, to edit a product is need the last or new data related with the same");
        Objects.requireNonNull(inINventory, "InInventory is null, to edit a product is need the last or new data with the same");
        Objects.requireNonNull(enabled, "Enabled is null, to edit a product is need the last or new data with the same");
        Objects.requireNonNull(max, "Max is null, to edit a product is need the last or new data with the same");
        Objects.requireNonNull(min, "Min is null, to edit a product is need the last or new data with the same");
        Objects.requireNonNull(price, "Price is null, to edit a product is need the last or new data with the same");

        appendChange(new ProductEdited(
            IDProduct.of(productID),
            PName.of(name),
            InInventory.of(inINventory),
            Enable.of(enabled),
            Max.of(max),
            Min.of(min),
            Price.of(price)
        )).apply();

    }

    public void deleteProduct(String shopID, String productID){

        Objects.requireNonNull(shopID, "The storeID is null, we need to know which store you want to remove the product from");
        Objects.requireNonNull(productID, "The productID is null, we need to know which product you want to remove from the store");

        appendChange(new ProductDeleted(ShopID.of(shopID),IDProduct.of(productID))).apply();

    }

    public void buyProduct(UUID uuid, CName clientName, Map<IDProduct, Integer> products){

        Objects.requireNonNull(uuid, "The UUID is null, we need to know the user id because we separate in two parts this UUID for save id type and id in the invoice");
        Objects.requireNonNull(clientName, "The clientName is null, we need to know the client name for save in the invoice his");
        Objects.requireNonNull(products, "The products are null, we need to know the products to save them on the invoice");

        // UUID Save example: "TI-1109876453"
        // UUID Save example: "CC-1109876453"
        // UUID Save example: "CE-1109876453"

        var id = uuid.value().split("-");
        appendChange(new ProductPurchased(id[0], id[1], clientName, products)).apply();

    }

    // + ------------------------ InstanceOf Shoputils ------------------------ + //

    protected ShopUtils utils = new ShopUtils();


}