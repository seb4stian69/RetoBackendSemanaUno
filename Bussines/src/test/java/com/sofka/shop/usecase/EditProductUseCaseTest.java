package com.sofka.shop.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.commands.EditProduct;
import com.sofka.shop.entities.Product;
import com.sofka.shop.events.ProductEdited;
import com.sofka.shop.events.ShopCreated;
import com.sofka.shop.gateway.ShopDomainEventRepository;
import com.sofka.shop.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditProductUseCaseTest {

    @Mock
    private ShopDomainEventRepository repository;
    @InjectMocks
    private EditProductUseCase useCase;

    @Test
    void editProduct(){

        // + ---------------------------------------- Arrange ---------------------------------------- + //

        var command = new EditProduct();

        command.setShopID("shop_1");
        command.setProductID("product1");
        command.setName("name_1");
        command.setInINventory(1);
        command.setEnabled(false);
        command.setMax(2);
        command.setMin(1);
        command.setPrice(100);

        // + ----------------------------------------   Act   ---------------------------------------- + //

        when(repository.getEventBy("shop_1")).thenReturn(history());

        // + ---------------------------------------- Asserts ---------------------------------------- + //

        StepVerifier.create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {

                    var event = (ProductEdited) domainEvent;
                    return "shop_1".equals(event.aggregateRootId())
                            && "product1".equals(event.getProductID().value())
                            && "name_1".equals(event.getName().value())
                            && 1 == event.getInINventory().value()
                            && false == event.getEnabled().value()
                            && 2 == event.getMax().value()
                            && 1 == event.getMin().value()
                            && 100 == event.getPrice().value();

                }).expectComplete().verify();
    }

    private Flux<DomainEvent> history(){

        var p1 = new Product( IDProduct.of("product1"), PName.of("Queso"),    InInventory.of(10), Enable.of(true), Max.of(100), Min.of(1), Price.of(10));
        var p2 = new Product( IDProduct.of("product2"), PName.of("Suero"),    InInventory.of(15), Enable.of(true), Max.of(100), Min.of(1), Price.of(10));
        var p3 = new Product( IDProduct.of("product3"), PName.of("Gaseosa"),  InInventory.of(8),  Enable.of(true), Max.of(100), Min.of(1), Price.of(10));
        var p4 = new Product( IDProduct.of("product4"), PName.of("Leche"),    InInventory.of(10), Enable.of(true), Max.of(100), Min.of(1), Price.of(10));
        var p5 = new Product( IDProduct.of("product5"), PName.of("Arequipe"), InInventory.of(9),  Enable.of(true), Max.of(100), Min.of(1), Price.of(10));

        List<Product> products = new ArrayList<>();

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);

        return Flux.just(
                new ShopCreated(products)
        );

    }

}