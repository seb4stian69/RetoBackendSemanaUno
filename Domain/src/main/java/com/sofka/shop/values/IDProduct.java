package com.sofka.shop.values;

import co.com.sofka.domain.generic.Identity;

public class IDProduct extends Identity {

    private IDProduct(String id) {
        super(id);
    }

    public static IDProduct of(String id) {
        return new IDProduct(id);
    }

}
