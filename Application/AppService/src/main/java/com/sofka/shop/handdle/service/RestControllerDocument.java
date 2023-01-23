package com.sofka.shop.handdle.service;

import com.sofka.shop.handdle.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("Products")
public class RestControllerDocument {

    private final DocumentService service;

    @Autowired
    public RestControllerDocument(DocumentService service) {
        this.service = service;
    }

    @GetMapping("/get")
    public Flux<ProductModel> findAllPage(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.findAll(pageable, Sort.by("idProduct"));
    }

}

