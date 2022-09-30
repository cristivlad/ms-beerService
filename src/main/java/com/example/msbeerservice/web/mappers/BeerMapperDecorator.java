package com.example.msbeerservice.web.mappers;

import com.example.msbeerservice.domain.Beer;
import com.example.msbeerservice.services.inventory.BeerInventoryService;
import com.example.msbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Beer assembleBeer(BeerDto dto) {
        return mapper.assembleBeer(dto);
    }

    @Override
    public BeerDto assembleBeerDto(Beer entity) {
        return mapper.assembleBeerDto(entity);
    }

    @Override
    public BeerDto assembleDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.assembleBeerDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return dto;
    }
}
