package com.sofka.shop.handdle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor
@Document(collection = "Products")
public class ProductModel {
    private String _id;
    private String idProduct;
    private String name;
    private Integer price;
    private Integer inInventory;
    private Boolean isEnabled;
    private Integer max;
    private Integer min;
}
