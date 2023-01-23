package com.sofka.shop.handdle;


import com.sofka.shop.handdle.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration@RestController
public class QueryHandler {

    // + ------------------------         Utils         ------------------------ + //

    Logger log = Logger.getLogger("SofkaShopSaid");
    private final ReactiveMongoTemplate template;

    // + ------------------------      Constructor       ------------------------ + //

    public QueryHandler(ReactiveMongoTemplate template) { this.template = template; }

    // + ------------------------    Command actions     ------------------------ + //

    @GetMapping("/users/list/{userID}")
    @Bean RouterFunction<ServerResponse> getUsers(){
        return RouterFunctions.route(GET("/users/list/{userID}"), request->
                template.findOne( getUserByID(request.pathVariable("userID")), UserModel.class, "Users" )
                .flatMap(user->
                        ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(Mono.just(user), UserModel.class))
                )
        );
    }

    @Bean RouterFunction<ServerResponse> getShop(){
        return RouterFunctions.route(GET("/shop/{shopID}"), request->
                template.findOne(getShopByID(request.pathVariable("shopID")), ShopModel.class, "shopview")
                .flatMap(shop->
                        ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(Mono.just(shop), ShopModel.class))
                )
        );
    }

    @GetMapping("/products")
    @Bean RouterFunction<ServerResponse> getProducts(){
        return RouterFunctions.route(GET("/products/{shopID}"), request->
                template.findOne(getShopByID(request.pathVariable("shopID")), PModel.class, "shopview")
                        .flatMap(products->
                                ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(Mono.just(products), PModel.class))
            )
        );
    }

    @Bean RouterFunction<ServerResponse> getProductByID(){
        return RouterFunctions.route(GET("/products/{shopID}/{productID}"), request->
                template.findOne(getProductsByID(request.pathVariable("shopID"), request.pathVariable("productID")), PModel.class, "shopview")
                        .flatMap(product->
                                ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(
                                            Mono.just(product.getProducts().get(request.pathVariable("productID"))),
                                            Product.class)
                                        )
                        )
        );
    }

    @GetMapping("/buy/get")
    @Bean RouterFunction<ServerResponse> getBuys(){
        return RouterFunctions.route(GET("/buy/get"), request->
                template.findAll( BuyModel.class, "invoiceview" )
                        .collectList().flatMap(buys->
                                ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(Flux.fromIterable(buys), BuyModel.class))
                        )
        );
    }

    @GetMapping("/buy/get/{userid}")
    @Bean RouterFunction<ServerResponse> getBuysByUser(){
        return RouterFunctions.route(GET("/buy/get/{userid}"), request->
                template.find(getBuysByIDUser(request.pathVariable("userid")), BuyModel.class, "invoiceview")
                        .collectList().flatMap(buys->
                                ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(Flux.fromIterable(buys), BuyModel.class))
                        )
        );
    }

    // + ------------------------     Query actions      ------------------------ + //

    private Query getUserByID(String userID){
        return new Query(
                Criteria.where("uuid.number").is(userID)
        );
    }

    public Query getShopByID(String shopID) {
        return new Query(
                Criteria.where("_id").is(shopID)
        );
    }

    private Query getProductsByID(String shopID, String productID) {
        System.out.println("products."+productID+".productID.uuid");
        return new Query(
                Criteria.where("_id").is(shopID).and("products."+productID+".productID.uuid").is(productID)
        );
    }

    private Query getBuysByIDUser(String userID) {
        return new Query(
                Criteria.where("idClient").is(userID)
        );
    }

}
