package com.example.msbeerservice.services;

import com.example.msbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID beerId);
    BeerDto createBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID id, BeerDto beerDto);
}
