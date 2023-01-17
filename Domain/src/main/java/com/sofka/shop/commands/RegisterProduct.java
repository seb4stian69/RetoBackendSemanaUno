package com.sofka.shop.commands;

import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

public class RegisterProduct extends Command {

    private String name;
    private Integer inINventory;
    private Boolean enabled;
    private Integer max;
    private Integer min;

}
