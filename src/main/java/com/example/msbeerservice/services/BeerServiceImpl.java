package com.example.msbeerservice.services;

import com.example.msbeerservice.domain.Beer;
import com.example.msbeerservice.repositories.BeerRepository;
import com.example.msbeerservice.web.mappers.BeerMapper;
import com.example.msbeerservice.web.model.BeerDto;
import com.example.msbeerservice.web.model.BeerPagedList;
import com.example.msbeerservice.web.model.BeerStyleEnum;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage.getContent().stream()
                .map(mapper::assembleBeerDto)
                .collect(toList()),
                PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;

    }

}
