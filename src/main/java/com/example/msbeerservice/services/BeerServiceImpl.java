package com.example.msbeerservice.services;

import com.example.msbeerservice.repositories.BeerRepository;
import com.example.msbeerservice.web.mappers.BeerMapper;
import com.example.msbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper mapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return mapper.assembleBeerDto(beerRepository.findById(beerId).orElseThrow(DataNotFoundException::new));
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        return mapper.assembleBeerDto(beerRepository.save(mapper.assembleBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        var beer = beerRepository.findById(id).orElseThrow(DataNotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return mapper.assembleBeerDto(beerRepository.save(beer));
    }

}
