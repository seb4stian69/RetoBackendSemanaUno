package com.sofka.shop.handdle.service;

import com.sofka.shop.handdle.model.ProductModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends ReactiveMongoRepository<ProductModel, String> {/*Interface del repositorio*/}
