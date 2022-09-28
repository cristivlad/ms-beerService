package com.example.msbeerservice.web.mappers;

import com.example.msbeerservice.domain.Beer;
import com.example.msbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    Beer assembleBeer(BeerDto dto);

    BeerDto assembleBeerDto(Beer entity);
}
