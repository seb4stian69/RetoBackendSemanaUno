package com.sofka.shop.commands;

import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter/* */@Setter
@AllArgsConstructor/* */@NoArgsConstructor

// Si el UUID es 0 listara todos las ventas realizadas caso contrario las ventas del usuario en particulas

public class CheckHistory extends Command {

    private String UUID;

}
