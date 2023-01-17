package com.sofka.shop.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

// Si el productID es 0 listara todos los productos disponibles caso contrario el producto

public class ListProduct {

    private String productID;

}
