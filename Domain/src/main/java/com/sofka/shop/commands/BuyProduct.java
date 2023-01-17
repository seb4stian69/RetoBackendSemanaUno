package com.sofka.shop.commands;

import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

/*

    {
        “date”: “current date with time”
        “idType”: “CC”,
        “id”: “103748422”,
        “clientName”: “Joy”,
        “products”:[{“idProduct”: "1", “quantity”: "150"}]
    }

*/

public class BuyProduct extends Command {

    private Instant date;
    private String idType;
    private String id;
    private String clientName;
    private Map<String, Integer> products;

}
