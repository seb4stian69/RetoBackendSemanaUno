package com.sofka.shop.Handdle.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class BuyModel {

    private Instant date = Instant.now();
    private String clientName;
    private String idType;
    private String clientid;
    private List<Product> products;

    @Getter@Setter
    private static class Product{
        private String idProduct;
        private Integer quantity;
    }

}
