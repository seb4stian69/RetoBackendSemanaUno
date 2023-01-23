package com.sofka.shop.handdle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class BuyModel {

    private String shopID;
    private Instant date;
    private String idType;
    private String idClient;
    private clientName clientName;
    private Map<String,Integer> productsPurchased;

}

@Getter/* */@Setter
class clientName{
    private String name;
    private String lastName;
}
