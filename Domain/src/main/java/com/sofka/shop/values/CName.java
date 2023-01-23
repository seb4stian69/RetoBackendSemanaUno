
package com.sofka.shop.values;

import co.com.sofka.domain.generic.ValueObject;

public class CName implements ValueObject<CName.Props> {

    private final String name;
    private final String lastName;

    public CName(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public static CName of(String name, String lastname){
        return new CName(name,lastname);
    }

    @Override
    public Props value() {
        return new Props(){
            @Override
            public String name() {
                return name;
            }
            @Override
            public String lastName() {
                return lastName;
            }
        };
    }

    public interface Props {
        String name();
        String lastName();
    }

}
