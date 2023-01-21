package com.sofka.shop.handdle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class ShopModel {

    private String shopID;
    private List<String> products;

}
