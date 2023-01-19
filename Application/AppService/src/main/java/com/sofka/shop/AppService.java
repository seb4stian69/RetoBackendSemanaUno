package com.sofka.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/*

    /users/list/{userID} <- GET                    | -> Complete

    /shop/${shopID} <- GET                         | -> Complete
    /shop/create <- POST                           | ->

    /products <- GET                               | -> Complete
    /products/{productID} <- GET By ID             | -> Complete
    /products/create <- POST                       | ->
    /products/put/{productid} <- PUT               | ->
    /products/delete/{productid} <- DELETE         | ->

    /buy/get <- GET                                | -> Complete
    /buy/get/{userid} <- GET By UserID             | -> Complete
    /buy/make <- POST                              | ->

*/

@SpringBootApplication
@EnableReactiveMongoRepositories
public class AppService {
    public static void main(String[] args) {
        SpringApplication.run(AppService.class, args);
    }
}
