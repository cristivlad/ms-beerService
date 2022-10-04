package com.example.msbeerservice.events;

import com.example.msbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 6450209765616191377L;

    private final BeerDto beerDto;
}
