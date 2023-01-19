package com.sofka.shop.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.shop.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class CreateShop extends Command {

    private String shopID;
    private Set<Product> products;

}
