package com.sofka.shop.commands;

import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class CreateShop extends Command {

    private Map<String, Integer> products;

}
