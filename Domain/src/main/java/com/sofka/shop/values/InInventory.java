package com.sofka.shop.values;

import co.com.sofka.domain.generic.ValueObject;

public class InInventory implements ValueObject<Integer> {

    private final Integer quantity;

    public InInventory(Integer quantity) {
        this.quantity = quantity;
    }

    public static InInventory of(Integer quantity) {
        return new InInventory(quantity);
    }

    @Override
    public Integer value() {
        return quantity;
    }

}
