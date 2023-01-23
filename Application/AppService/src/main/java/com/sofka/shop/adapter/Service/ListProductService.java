package com.sofka.shop.adapter.Service;

import com.sofka.shop.gateway.ModelProduct;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ListProductService implements com.sofka.shop.gateway.ListProductService {

    private final ReactiveMongoTemplate template;

    public ListProductService(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Flux<ModelProduct> obtenerProductos() {
        return template.findAll(ModelProduct.class, "Products");
    }

}
