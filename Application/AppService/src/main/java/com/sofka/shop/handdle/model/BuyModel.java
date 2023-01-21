package com.sofka.shop.handdle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class BuyModel {

    private String shopID;
    private Instant date = Instant.now();
    private String idType;
    private String idClient;
    private String clientName;
    private Map<String,Integer> products;

}

