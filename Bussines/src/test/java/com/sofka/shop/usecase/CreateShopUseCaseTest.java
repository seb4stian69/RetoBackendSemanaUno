package com.sofka.shop.usecase;

import com.sofka.shop.commands.CreateShop;
import com.sofka.shop.entities.Product;
import com.sofka.shop.events.ShopCreated;
import com.sofka.shop.gateway.ListProductService;
import com.sofka.shop.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateShopUseCaseTest {

    @Mock
    private ListProductService service;
    @InjectMocks
    private CreateShopUseCase useCase;

    @Test
    void createShop(){

        // + ---------------------------------------- Arrange ---------------------------------------- + //

        var command = new CreateShop();
        /* -> */command.setShopID("shop_1");

        // + ----------------------------------------   Act   ---------------------------------------- + //

        when(service.obtenerProductos()).thenReturn(history());

        // + ---------------------------------------- Asserts ---------------------------------------- + //

        StepVerifier.create(useCase.apply(Mono.just(command)))
                .expectNextMatches(domainEvent -> {
                    var event = (ShopCreated) domainEvent;
                    return "shop_1".equals(event.aggregateRootId())
                            && 5 == event.getProducts().size();
                }
        ).expectComplete().verify();
    }

    private Flux<Product> history(){
        return Flux.just(
            new Product( IDProduct.of("product1"), PName.of("Queso"),    InInventory.of(10), Enable.of(true), Max.of(100), Min.of(1), Price.of(10)),
            new Product( IDProduct.of("product2"), PName.of("Suero"),    InInventory.of(15), Enable.of(true), Max.of(100), Min.of(1), Price.of(10)),
            new Product( IDProduct.of("product3"), PName.of("Gaseosa"),  InInventory.of(8),  Enable.of(true), Max.of(100), Min.of(1), Price.of(10)),
            new Product( IDProduct.of("product4"), PName.of("Leche"),    InInventory.of(10), Enable.of(true), Max.of(100), Min.of(1), Price.of(10)),
            new Product( IDProduct.of("product5"), PName.of("Arequipe"), InInventory.of(9),  Enable.of(true), Max.of(100), Min.of(1), Price.of(10))
        );
    }

}