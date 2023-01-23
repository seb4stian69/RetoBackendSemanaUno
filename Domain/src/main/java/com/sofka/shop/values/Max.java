package com.sofka.shop.values;

import co.com.sofka.domain.generic.ValueObject;

public class Max implements ValueObject<Integer> {

    private final Integer quantity;

    public Max(Integer quantity) {
        this.quantity = quantity;
    }

    public static Max of(Integer quantity) {
        return new Max(quantity);
    }

    @Override
    public Integer value() {
        return quantity;
    }
    
}
