package com.sofka.shop.Handdle.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class ShopModel {

    private String shopid;
    private List<String> products;

}
