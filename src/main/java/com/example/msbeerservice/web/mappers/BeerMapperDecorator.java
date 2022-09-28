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
        BeerDto dto = mapper.assembleBeerDto(entity);
        dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(entity.getId()));
        System.out.println(dto);
        return dto;
    }
}
