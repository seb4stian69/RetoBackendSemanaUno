package com.sofka.shop.gateway;

import com.sofka.shop.entities.Product;
import reactor.core.publisher.Flux;

public interface ListProductService {
    Flux<Product> obtenerProductos();
}
