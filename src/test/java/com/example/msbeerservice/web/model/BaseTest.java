package com.example.msbeerservice.web.model;

import java.math.BigDecimal;

import static java.util.UUID.randomUUID;

public class BaseTest {

    BeerDto getBeerDto() {
        return BeerDto.builder().beerName("My Beer").beerStyle(BeerStyleEnum.IPA)
                .price(new BigDecimal("2.99")).id(randomUUID())
                .upc(1241L).build();
    }
}
