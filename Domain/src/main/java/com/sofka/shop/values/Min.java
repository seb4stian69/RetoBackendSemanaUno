package com.sofka.shop.values;

import co.com.sofka.domain.generic.ValueObject;

public class Min implements ValueObject<Integer> {

    private final Integer quantity;

    public Min(Integer quantity) {
        this.quantity = quantity;
    }

    public static Min of(Integer quantity) {
        return new Min(quantity);
    }

    @Override
    public Integer value() {
        return quantity;
    }

}