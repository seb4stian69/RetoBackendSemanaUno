package com.sofka.shop.values;

import co.com.sofka.domain.generic.ValueObject;

public class Enable implements ValueObject<Boolean> {

    private final Boolean isEnable;

    public Enable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public static Enable of(Boolean isEnable) {
        return new Enable(isEnable);
    }

    @Override
    public Boolean value() {
        return isEnable;
    }

}
