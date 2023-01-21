package com.sofka.shop.handdle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

@Document(collection = "Products")
public class ProductModel {

    @Id
    private String _id;
    private String shopID;
    private String productID;
    private String name;
    private Integer inINventory;
    private Boolean enabled;
    private Integer max;
    private Integer min;
    private Integer price;

    public void update(ProductModel p){

        this.productID = p.getProductID();
        this.name = p.getName();
        this.inINventory = p.getInINventory();
        this.enabled = p.getEnabled();
        this.max = p.getMax();
        this.min = p.getMin();


    }

    public String getIDBase(){
        return this._id;
    }

}
