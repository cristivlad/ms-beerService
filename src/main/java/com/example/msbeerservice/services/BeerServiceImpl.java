package com.example.msbeerservice.services;

import com.example.msbeerservice.web.model.BeerDto;
import com.example.msbeerservice.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.IPA)
                .build();
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID id, BeerDto beerDto) {
        log.debug("Updating a beer...");
    }

    @Override
    public void deleteBeer(UUID id) {
        log.debug("Deleting a beer...");
    }
}
