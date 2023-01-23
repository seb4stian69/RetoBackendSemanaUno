package com.sofka.shop.entities;

import co.com.sofka.domain.generic.Entity;
import com.sofka.shop.values.CName;
import com.sofka.shop.values.UUID;
import com.sofka.shop.values.Username;
import lombok.Getter;
import lombok.Setter;

// UUID Es la combinacion del numero de identificacion con el tipo [Ejemplo -> CC1102323845]

@Getter/* */@Setter
public class Client extends Entity<UUID> {

    private CName name;
    private Username username;

    public  Client(UUID id,CName name, Username username) {
        super(id);
        this.name = name;
        this.username = username;
    }



}
