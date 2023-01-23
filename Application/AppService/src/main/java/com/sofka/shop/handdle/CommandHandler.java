package com.sofka.shop.handdle;

import com.sofka.shop.commands.*;
import com.sofka.shop.usecase.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration @AllArgsConstructor
public class CommandHandler {

    private final ReactiveMongoTemplate template;
    private final IntegrationHandle integrationHandle;
    private final ErrorHandler errorHandler;

    @Bean RouterFunction<ServerResponse> createShop(CreateShopUseCase usecase){
        return route(POST("/shop/create").and(accept(MediaType.APPLICATION_JSON)),
            request -> usecase.andThen(integrationHandle)
                .apply(request.bodyToMono(CreateShop.class))
                .then(ServerResponse.ok().build())
                .onErrorResume(errorHandler::badRequest)
        );
    }

    @Bean RouterFunction<ServerResponse> createProduct(RegisterProductUseCase usecase){
        return route(POST("/products/create").and(accept(MediaType.APPLICATION_JSON)),
            request -> usecase.andThen(integrationHandle)
                .apply(request.bodyToMono(RegisterProduct.class))
                .then(ServerResponse.ok().build())
                .onErrorResume(errorHandler::badRequest)
        );
    }

    @Bean RouterFunction<ServerResponse> updateProduct(EditProductUseCase usecase){
        return route(PUT("/product/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(EditProduct.class))
                        .then(ServerResponse.ok().build())
                        .onErrorResume(errorHandler::badRequest)
        );
    }

    @Bean RouterFunction<ServerResponse> deleteProduct(DeleteProductUseCase usecase){
        return route(DELETE("/product/delete").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(DeleteProduct.class))
                        .then(ServerResponse.ok().build())
                        .onErrorResume(errorHandler::badRequest)
        );
    }

    @Bean RouterFunction<ServerResponse> buyProduct(BuyProductUseCase usecase){
        return route(POST("/buy/make").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(BuyProduct.class))
                        .then(ServerResponse.ok().build())
                        .onErrorResume(errorHandler::badRequest)
        );
    }

    // + ------------------------     Query actions      ------------------------ + //

}
