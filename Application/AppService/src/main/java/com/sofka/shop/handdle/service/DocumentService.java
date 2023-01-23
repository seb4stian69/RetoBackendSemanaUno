package com.sofka.shop.handdle.service;

import com.sofka.shop.handdle.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;


@Service
public class DocumentService{

    private final Logger log = Logger.getLogger(String.valueOf(DocumentService.class));
    private final ReactiveSortRepo repository;

    @Autowired
    public DocumentService(ReactiveSortRepo repository) {
        this.repository = repository;
    }

    public Flux<ProductModel> findAll(Pageable pageable, Sort sort) {
        return repository.applyPagination(repository.findAll(sort), pageable);
    }

}
