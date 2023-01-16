package com.sofka.shop.values;

import co.com.sofka.domain.generic.ValueObject;

public class PName implements ValueObject<String> {

    private final String name;

    public PName(String name) {
        this.name = name;
    }

    public static PName of(String name) {
        return new PName(name);
    }

    @Override
    public String value() {
        return name;
    }

}
