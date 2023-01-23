package com.sofka.shop.handdle.model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Product {

    productID productID;
    name name;
    inInventory inInventory;
    isEnabled isEnabled;
    max max;
    min min;
    price price;

}

@Getter@Setter
class productID {
    String uuid;
}
@Getter@Setter
class name {
    String name;
}
@Getter@Setter
class inInventory {
    int quantity;
}
@Getter@Setter
class isEnabled {
    boolean isEnable;
}
@Getter@Setter
class max {
    Integer quantity;
}
@Getter@Setter
class min {
    Integer quantity;
}
@Getter@Setter
class price {
    Integer quantity;
}