package com.sofka.shop.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.shop.commands.BuyProduct;
import com.sofka.shop.entities.Product;
import com.sofka.shop.events.ProductsPurchased;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuyProductUseCaseTest {

    // + -------------- Utils Attributes -------------- + //
    private Map<String, Integer> productsPurchased = new HashMap<String, Integer>();
    // + -------------- Utils Attributes -------------- + //

    @Mock
    private ShopDomainEventRepository repository;
    @InjectMocks
    private BuyProductUseCase useCase;

    @Test
    void buyProduct() {

        // + ---------------------------------------- Arrange ---------------------------------------- + //

        var command = new BuyProduct();
        /* -> */ command.setShopID("shop_1");
        /* -> */ command.setIdType("CC");
        /* -> */ command.setIdClient("1104254557");
        /* -> */ command.setClientName("Sebastian Santis");

        productsPurchased.put("product1", 2); //20
        productsPurchased.put("product2", 3); //30
        productsPurchased.put("product3", 1); //10
        productsPurchased.put("product4", 4); //40

        /* ->20+30+10+40 = 100 */ command.setProducts(productsPurchased);

        // + ----------------------------------------   Act   ---------------------------------------- + //

        when(repository.getEventBy("shop_1")).thenReturn(history());

        // + ---------------------------------------- Asserts ---------------------------------------- + //

        StepVerifier.create(useCase.apply(Mono.just(command)))
        .expectNextMatches(domainEvent -> {

            var event = (ProductsPurchased) domainEvent;
            return "shop_1".equals(event.aggregateRootId())
                    && "CC".equals(event.getIdType())
                    && "1104254557".equals(event.getId())
                    && "Sebastian Santis".equals(event.getClientName().value().name() + " " + event.getClientName().value().lastName())
                    && 4 == event.getProducts().size()
            ;

        }).expectComplete().verify();

    }

    private Flux<DomainEvent> history(){

        var p1 = new Product( IDProduct.of("product1"), PName.of("Queso"),    InInventory.of(10), Enable.of(true), Max.of(100), Min.of(1), Price.of(10));
        var p2 = new Product( IDProduct.of("product2"), PName.of("Suero"),    InInventory.of(15), Enable.of(true), Max.of(100), Min.of(1), Price.of(10));
        var p3 = new Product( IDProduct.of("product3"), PName.of("Gaseosa"),  InInventory.of(8),  Enable.of(true), Max.of(100), Min.of(1), Price.of(10));
        var p4 = new Product( IDProduct.of("product4"), PName.of("Leche"),    InInventory.of(10), Enable.of(true), Max.of(100), Min.of(1), Price.of(10));
        var p5 = new Product( IDProduct.of("product5"), PName.of("Arequipe"), InInventory.of(9),  Enable.of(true), Max.of(100), Min.of(1), Price.of(10));

        List<Product> products = new ArrayList<Product>();

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
