package com.example.msbeerservice.services;

import com.example.msbeerservice.web.model.BeerDto;
import com.example.msbeerservice.web.model.BeerPagedList;
import com.example.msbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand);
    BeerDto createBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID id, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, boolean showInventoryOnHand);

    BeerDto getByUpc(String upc);
}
