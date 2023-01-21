package com.sofka.shop.handdle.service;

import com.sofka.shop.handdle.model.BuyModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBuys extends ReactiveMongoRepository<BuyModel, String> {
}
