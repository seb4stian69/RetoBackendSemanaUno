package com.sofka.shop.gateway;

import com.sofka.shop.values.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter/* */@Setter
@NoArgsConstructor/* */@AllArgsConstructor

public class ModelProduct {

    private IDProduct productID;
    private PName name;
    private InInventory inInventory;
    private Enable isEnabled;
    private Max max;
    private Min min;
    private Price price;

}
