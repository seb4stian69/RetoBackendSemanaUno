package com.sofka.shop.utils;

import com.sofka.shop.entities.Product;
import com.sofka.shop.values.IDProduct;
import com.sofka.shop.values.InInventory;

import java.util.List;

public class ShopUtils {

    public ShopUtils() {/*void*/}

    public Product findProductById(IDProduct id, List<Product> products) {
        return products.stream()
                .filter( product -> id.equals(product.getProductID()) )
                .findFirst()
                .orElse(null);
    }

    public void subtractProduct(IDProduct id, List<Product> products, Integer quantity) {

        var product = findProductById(id, products);

        if(product.getInInventory().value() < quantity) {
            throw new IllegalArgumentException("You try to buy more products than are currently available");
        }else{
            product.setInInventory(InInventory.of( product.getInInventory().value() - quantity ));
        }

    }

    public void isEnable(IDProduct id, List<Product> products){
        products.forEach( product ->{
            if(Boolean.FALSE.equals(findProductById(id, products).getIsEnabled().value())){
                throw new IllegalStateException("This product isnt already enabled");
            }
        });
    }

    public void isInInventory(IDProduct id, List<Product> products){
        products.forEach( product ->{
            if(1 > findProductById(id, products).getInInventory().value()){
                throw new IllegalStateException("This product isnt in inventory");
            }
        });
    }

    public void isMax(IDProduct id, Integer quantity, List<Product> products){
        products.forEach( product ->{
            if(quantity > findProductById(id, products).getMax().value()){
                throw new IllegalStateException("You try to buy more than possible");
            }
        });
    }

    public void isMin(IDProduct id, Integer quantity, List<Product> products){
        products.forEach( product -> {
            if (quantity < findProductById(id, products).getMin().value()) {
                throw new IllegalStateException("You try to buy less than possible");
            }
        });
    }

    public String joinUUID(String typeID, String id){
        return typeID + "-" + id;
    }

    public String joinClientName(String name, String lastname){
        return name + " " + lastname;
    }

    public void detectedProblems(IDProduct idProduct, Integer quantity, List<Product> products){
        this.isEnable(idProduct, products);
        this.isInInventory(idProduct, products);
        this.isMax(idProduct,quantity, products);
        this.isMin(idProduct,quantity, products);
    }

}
