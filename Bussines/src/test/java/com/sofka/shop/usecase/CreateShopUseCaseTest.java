package com.sofka.shop.usecase;

import com.sofka.shop.commands.CreateShop;
import com.sofka.shop.events.ShopCreated;
import com.sofka.shop.gateway.ListProductService;
import com.sofka.shop.gateway.ModelProduct;
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

    private Flux<ModelProduct> history(){
        return Flux.just(
            new ModelProduct( "product1", "Queso",    10, true, 100, 1, 10),
            new ModelProduct( "product2", "Suero",    15, true, 100, 1, 10),
            new ModelProduct( "product3", "Gaseosa",  8, true, 100, 1, 10),
            new ModelProduct( "product4", "Leche",    10, true, 100, 1, 10),
            new ModelProduct( "product5", "Arequipe", 9, true, 100, 1, 10)
        );
    }

}