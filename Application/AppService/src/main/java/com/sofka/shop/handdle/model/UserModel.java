package com.sofka.shop.handdle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class UserModel {

    private UUID uuid;
    private Name name;
    private String email;
    private String username;

    // Especials classes //

    @Getter@Setter
    public static class UUID{
        private String type;
        private String number;
    }

    @Getter@Setter
    public static class Name{
        private String firstname;
        private String lastname;
    }

}
