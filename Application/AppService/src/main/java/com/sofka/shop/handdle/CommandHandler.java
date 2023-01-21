package com.sofka.shop.handdle;

import com.sofka.shop.handdle.model.BuyModel;
import com.sofka.shop.handdle.model.ProductModel;
import com.sofka.shop.commands.BuyProduct;
import com.sofka.shop.commands.CreateShop;
import com.sofka.shop.commands.RegisterProduct;
import com.sofka.shop.needed.BuyProductN;
import com.sofka.shop.needed.CreateShopN;
import com.sofka.shop.needed.RegisterProductN;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration @AllArgsConstructor@EnableSwagger2@RestController
public class CommandHandler {

    private final IntegrationHandle integrationHandle;
    private ReactiveMongoTemplate template;
    private ErrorHandler errorHandler;
    private QueryHandler query;

    @PostMapping("/shop/create")
    @Bean public RouterFunction<ServerResponse> createShop() {
        return route(
                POST("/shop/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> template.save(request.bodyToMono(CreateShop.class), "Shops")
                        .then(ServerResponse.ok().build())
        );
    }

    @PostMapping("/products/create")
    @Bean public RouterFunction<ServerResponse> createProduct() {
        return route(
                POST("/products/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> template.save(request.bodyToMono(RegisterProduct.class), "Products")
                        .then(ServerResponse.ok().build())
        );
    }

    @DeleteMapping("/products/delete/{shopid}/{productid}")
    @Bean public RouterFunction<ServerResponse> deleteProduct() {
        return route(
                DELETE("/products/delete/{shopid}/{productid}").and(accept(MediaType.APPLICATION_JSON)),
                request -> template.findAndRemove(
                        getProductsByID(request.pathVariable("shopid"),request.pathVariable("productid")),
                        ProductModel.class,
                        "Products"
                ).then(ServerResponse.ok().build()));
    }

    @PostMapping("/buy/make")
    @Bean public RouterFunction<ServerResponse> purchaseProducts() {
        return route(
                POST("/buy/make").and(accept(MediaType.APPLICATION_JSON)),
                request -> template.save(request.bodyToMono(BuyProduct.class), "Buys")
                        .then(ServerResponse.ok().build())
        );
    }

    // * Querys * //

    private Query getProductsByID(String shopID, String productID) {
        return new Query(
                Criteria.where("productID").is(productID).and("shopID").is(shopID)
        );
    }

}
