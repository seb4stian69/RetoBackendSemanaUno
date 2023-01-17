package com.sofka.shop.values;

import co.com.sofka.domain.generic.ValueObject;

public class Price implements ValueObject<Integer> {

    private final Integer quantity;

    public Price(Integer quantity) {
        this.quantity = quantity;
    }

    public static Price of(Integer quantity) {
        return new Price(quantity);
    }

    @Override
    public Integer value() {
        return quantity;
    }

}
