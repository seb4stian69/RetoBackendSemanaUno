package com.sofka.shop.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @AllArgsConstructor @Getter @Setter
public class ModelProduct {

    private final String idProduct;
    private String name;
    private Integer inInventory;
    private Boolean isEnabled;
    private Integer max;
    private Integer min;
    private Integer price;

}
