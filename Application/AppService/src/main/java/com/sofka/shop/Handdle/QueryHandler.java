package com.sofka.shop.Handdle;


import com.sofka.shop.Handdle.Model.BuyModel;
import com.sofka.shop.Handdle.Model.ProductModel;
import com.sofka.shop.Handdle.Model.ShopModel;
import com.sofka.shop.Handdle.Model.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.logging.Logger;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class QueryHandler {

    // + ------------------------         Utils         ------------------------ + //

    Logger log = Logger.getLogger("SofkaShopSaid");
    private final ReactiveMongoTemplate template;

    // + ------------------------      Constructor       ------------------------ + //

    public QueryHandler(ReactiveMongoTemplate template) { this.template = template; }

    // + ------------------------    Command actions     ------------------------ + //

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
                template.findOne(getShopByID(request.pathVariable("shopID")), ShopModel.class, "Shops")
                .flatMap(shop->
                        ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(Mono.just(shop), ShopModel.class))
                )
        );
    }

    @Bean RouterFunction<ServerResponse> getProducts(){
        return RouterFunctions.route(GET("/products"), request->
            template.findAll( ProductModel.class, "Products" )
            .collectList().flatMap(products->
                        ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(Flux.fromIterable(products), ProductModel.class))
            )
        );
    }

    @Bean RouterFunction<ServerResponse> getProductByID(){
        return RouterFunctions.route(GET("/products/{productID}"), request->
            template.findOne(getProductsByID(request.pathVariable("productID")),ProductModel.class, "Products")
                    .flatMap(product->
                        ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(Mono.just(product), ProductModel.class))
                    )
        );
    }

    @Bean RouterFunction<ServerResponse> getBuys(){
        return RouterFunctions.route(GET("/buy/get"), request->
                template.findAll( BuyModel.class, "Buys" )
                        .collectList().flatMap(buys->
                                ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(Flux.fromIterable(buys), BuyModel.class))
                        )
        );
    }

    @Bean RouterFunction<ServerResponse> getBuysByUser(){
        return RouterFunctions.route(GET("/buy/get/{userid}"), request->
                template.find(getBuysByIDUser(request.pathVariable("userid")), BuyModel.class, "Buys")
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

    private Query getShopByID(String shopID) {
        return new Query(
                Criteria.where("shopid").is(shopID)
        );
    }

    private Query getProductsByID(String productID) {
        return new Query(
                Criteria.where("productid").is(productID)
        );
    }

    private Query getBuysByIDUser(String userID) {
        return new Query(
                Criteria.where("clientid").is(userID)
        );
    }

}
