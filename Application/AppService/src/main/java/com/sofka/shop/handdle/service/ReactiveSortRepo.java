package com.sofka.shop.handdle.service;

import com.sofka.shop.handdle.model.ProductModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public interface ReactiveSortRepo extends ReactiveMongoRepository<ProductModel, String> {

    default Flux<ProductModel> applyPagination(Flux<ProductModel> persons, Pageable pageable) {

        return persons.buffer(pageable.getPageSize(), (pageable.getPageNumber()+1))
                .elementAt(pageable.getPageNumber(), new ArrayList<>())
                .flatMapMany(Flux::fromIterable);

    }

}
