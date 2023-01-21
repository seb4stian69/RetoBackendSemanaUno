package com.sofka.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*

    | -> [ /users/list/{userID} <- GET                          | -> Complete - work

    | -> [ /shop/${shopID} <- GET                               | -> Complete - work
    | -> [ /shop/create <- POST                                 | -> Complete - work [products void]

    | -> [ /products <- GET                                     | -> Complete - work
    | -> [ /Products/get?page={}&size={} <- GET Paginacion      | -> Complete - work
    | -> [ /products/{productID} <- GET By ID                   | -> Complete - work
    | -> [ /products/create <- POST                             | -> Complete - work
    | -> [ /Products/update/{productid} <- PUT                  | -> Complete - work
    | -> [ /products/delete/{shopID}/{productID} <- DELETE      | -> Complete - work

    | -> [ /buy/get <- GET                                      | -> Complete - work
    | -> [ /buy/get/{userid} <- GET By UserID                   | -> Complete - work
    | -> [ /buy/make <- POST                                    | -> se registra la compra pero [No se descuentan los productos]

*/

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableSwagger2
public class AppService {
    public static void main(String[] args) {
        SpringApplication.run(AppService.class, args);
    }
}
