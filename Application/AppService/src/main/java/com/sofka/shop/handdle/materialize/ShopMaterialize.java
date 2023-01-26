package com.sofka.shop.handdle.materialize;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.entities.Product;
import com.sofka.shop.events.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.HashMap;

@Configuration
public class ShopMaterialize {

    private static final String COLLECTION_VIEW = "shopview";
    private static final String COLLECTION_VIEW_TWO = "invoiceview";
    private static final String ATTRIBUTE_PRODUCTS = "products";

    private final ReactiveMongoTemplate template;

    public ShopMaterialize(ReactiveMongoTemplate template) {this.template = template;}

    @EventListener
    public void handleShopCreated(ShopCreated event){
        var data = new HashMap<>();
        data.put("_id", event.aggregateRootId());
        var products = new HashMap<String, Product>();
        for (Product eventProduct : event.getProducts()) {
            products.put(eventProduct.getProductID().value(), eventProduct);
        }
        data.put(ATTRIBUTE_PRODUCTS, products);
        template.save(data, COLLECTION_VIEW).block();
    }

    @EventListener
    public void handleProductRegistered(ProductRegistered event){
        var data = new Update();
        data.set(ATTRIBUTE_PRODUCTS+"."+event.getProduct().getProductID().value(), event.getProduct());
        template.updateFirst(getFilterByAggregateId(event),data, COLLECTION_VIEW).block();
    }

    @EventListener
    public void handleProductEdited(ProductEdited event){
        var data = new Update();
        data.set(ATTRIBUTE_PRODUCTS+"."+event.getProductID().value(), event.getProduct());
        template.updateFirst(getFilterByAggregateId(event),data, COLLECTION_VIEW).block();
    }

    @EventListener
    public void handleProductDeleted(ProductDeleted event){
        var data = new Update();
        data.unset(ATTRIBUTE_PRODUCTS+"."+event.getProductId());
        template.updateFirst(getFilterByAggregateId(event),data, COLLECTION_VIEW).block();
    }

    @EventListener
    public void handleProductPurchased(ProductsPurchased event){

        var dataShop = new Update();
        var dataInvoice = new HashMap<>();

        dataInvoice.put("_id", SendID.cadenaAleatoria(35));
        dataInvoice.put("shopID", event.aggregateRootId());
        dataInvoice.put("productsPurchased", event.getProducts());
        dataInvoice.put("date",event.getDate());
        dataInvoice.put("idType", event.getIdType());
        dataInvoice.put("idClient", event.getId());
        dataInvoice.put("clientName", event.getClientName());

        event.getProducts().forEach((productid,quantity) ->
            dataShop.inc(ATTRIBUTE_PRODUCTS+"."+productid+".inInventory.quantity", -quantity)
        );

        template.updateFirst(getFilterByAggregateId(event),dataShop, COLLECTION_VIEW).block();
        template.save(dataInvoice, COLLECTION_VIEW_TWO).block();

    }

    // + ------------------------     Query actions      ------------------------ + //

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }

}
