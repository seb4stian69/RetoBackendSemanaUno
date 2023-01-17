package com.sofka.shop.values;

import co.com.sofka.domain.generic.Identity;

public class ShopID extends Identity {

    public ShopID(String id) {
        super(id);
    }

    public static ShopID of(String id) {
        return new ShopID(id);
    }

}
