package com.sofka.shop.gateway;

import com.sofka.shop.entities.Product;
import com.sofka.shop.values.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CovertToProductEntities {

    public static List<Product> convert (List<ModelProduct> products){

        List<Product> productsList = new ArrayList<Product>();

        products.forEach(product ->{

            var productSend =  new Product(
                    IDProduct.of(product.getIdProduct()),
                    PName.of(product.getName()),
                    InInventory.of(product.getInInventory()),
                    Enable.of(product.getIsEnabled()),
                    Max.of(product.getMax()),
                    Min.of(product.getMin()),
                    Price.of(product.getPrice())
            );

            productsList.add(productSend);

        });

        return productsList;

    }

}
