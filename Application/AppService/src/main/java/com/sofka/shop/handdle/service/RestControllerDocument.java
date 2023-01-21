package com.sofka.shop.handdle.service;

import com.sofka.shop.commands.BuyProduct;
import com.sofka.shop.handdle.model.BuyModel;
import com.sofka.shop.handdle.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("Products")
public class RestControllerDocument {

    private final DocumentService service;
    private final Logger log = Logger.getLogger(String.valueOf(RestControllerDocument.class));
    @Autowired
    public RestControllerDocument(DocumentService service) {
        this.service = service;
    }

    // Actualizar servicios
    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<ProductModel>> updateDocument(@PathVariable String id, @RequestBody ProductModel pmodel) {
        return service.updateDocument(id, pmodel);
    }

    @GetMapping("/get")
    public Flux<ProductModel> findAllPage(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return service.findAll(pageable, Sort.by("productID"));
    }


}

