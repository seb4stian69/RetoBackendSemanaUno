package com.sofka.shop.values;

import co.com.sofka.domain.generic.Identity;

public class IDProduct extends Identity {

    private IDProduct(String uuid) {
        super(uuid);
    }

    public static IDProduct of(String uuid) {
        return new IDProduct(uuid);
    }

}
