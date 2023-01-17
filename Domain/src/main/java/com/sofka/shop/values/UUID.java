package com.sofka.shop.values;

import co.com.sofka.domain.generic.Identity;

public class UUID extends Identity {

    private UUID(String id) {
        super(id);
    }

    public static UUID of(String id) {
        return new UUID(id);
    }

}
