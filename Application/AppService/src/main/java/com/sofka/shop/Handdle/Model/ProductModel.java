package com.sofka.shop.Handdle.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class ProductModel {

    private String productid;
    private String name;
    private Integer inInventory;
    private Boolean enable;
    private Integer max;
    private Integer min;
    private Integer price;

}
