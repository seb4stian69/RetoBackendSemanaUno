package com.sofka.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*

    | -> [ /users/list/{userID} <- GET                          | -> Complete - work

    | -> [ /shop/${shopID} <- GET                               | -> Complete - work
    | -> [ /shop/create <- POST                                 | -> Complete - work

    | -> [ /products/{shopID} <- GET                            | -> Complete - work
    | -> [ /Products/get?page={}&size={} <- GET Paginacion      | -> Complete - work
    | -> [ /products/{shopID}/{productID} <- GET By ID          | -> Complete - work
    | -> [ /products/create <- POST                             | -> Complete - work
    | -> [ /Products/update <- PUT                              | -> Complete - work
    | -> [ /products/delete <- DELETE                           | -> Complete - work

    | -> [ /buy/get <- GET                                      | -> Complete - work
    | -> [ /buy/get/{userid} <- GET By UserID                   | -> Complete - work
    | -> [ /buy/make <- POST                                    | -> Complete - work

*/

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableSwagger2
public class AppService {
    public static void main(String[] args) {
        SpringApplication.run(AppService.class, args);
    }
}
