package com.example.msbeerservice.web.mappers;

import com.example.msbeerservice.domain.Beer;
import com.example.msbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    Beer assembleBeer(BeerDto dto);

    BeerDto assembleBeerDto(Beer entity);
}
