package com.sofka.shop.handdle.service;

import com.sofka.shop.handdle.model.BuyModel;
import com.sofka.shop.handdle.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;


@Service
public class DocumentService{

    private final Logger log = Logger.getLogger(String.valueOf(DocumentService.class));
    private final ReactiveSortRepo repository;
    private final DocumentRepository documentRepository;
    private final IBuys buyservices;

    @Autowired
    public DocumentService(ReactiveSortRepo repository, DocumentRepository documentRepository, IBuys buyservices) {
        this.repository = repository;
        this.documentRepository = documentRepository;
        this.buyservices = buyservices;
    }

    public Mono<ResponseEntity<ProductModel>> updateDocument(String id, ProductModel productSend) {

        return documentRepository.findById(id).flatMap(productFind -> {

            productFind.setName(productSend.getName());
            productFind.setInINventory(productSend.getInINventory());
            productFind.setEnabled(productSend.getEnabled());
            productFind.setMax(productSend.getMax());
            productFind.setMin(productSend.getMin());
            productFind.setPrice(productSend.getPrice());

            return documentRepository.save(productFind);

        })
        .map(update -> ResponseEntity.ok().<ProductModel>build())
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    /*public Mono<ResponseEntity<ProductModel>> buyMake(BuyModel buy) {

        buy.getProducts().forEach(p->documentRepository.findById(p.get_id())
                .flatMap(productModel -> {
                    log.info("Hola");
                productModel.setInINventory(productModel.getInINventory() - p.getQuantity());
                return documentRepository.save(productModel);
            }).subscribe()
        );

        return Mono.just(ResponseEntity.ok().<ProductModel>build());

    }*/

    /*public Mono<ResponseEntity<ProductModel>> cd(BuyModel buy) {

        var products = buy.getProducts();
        List<String> idProductIds = new ArrayList<>();

        products.forEach(product ->{
            idProductIds.add(product.get_id());
        });

        idProductIds.forEach(p->{
            documentRepository.findById(p).subscribe();
        });

        return Mono.just(ResponseEntity.ok().<ProductModel>build());
    }*/

    public Flux<ProductModel> findAll(Pageable pageable, Sort sort) {
        return repository.applyPagination(repository.findAll(sort), pageable);
    }

}
