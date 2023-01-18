package com.sofka.shop.gateway;

import reactor.core.publisher.Flux;

public interface ListProductService {
    Flux<ModelProduct> obtenerProductos();
}
