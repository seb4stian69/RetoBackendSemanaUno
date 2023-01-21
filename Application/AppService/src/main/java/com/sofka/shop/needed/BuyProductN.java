package com.sofka.shop.needed;

import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class BuyProductN {

    private String shopID;
    private Instant date;
    private String idType;
    private String idClient;
    private String clientName;
    private Map<String, Integer> products = new HashMap<String, Integer>();


}
