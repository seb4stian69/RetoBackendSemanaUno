package com.sofka.shop.handdle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class PModel {
    private Map<String, Product> products;
}
