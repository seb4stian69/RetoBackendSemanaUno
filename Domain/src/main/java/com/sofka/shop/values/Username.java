package com.sofka.shop.values;

import co.com.sofka.domain.generic.ValueObject;

public class Username implements ValueObject<String> {

    private final String alias;

    public Username(String alias) {
        this.alias = alias;
    }

    public static Username of(String alias) {
        return new Username(alias);
    }

    @Override
    public String value() {
        return alias;
    }

}
