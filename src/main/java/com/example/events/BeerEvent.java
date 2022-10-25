package com.example.events;

import com.example.msbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 6450209765616191377L;

    private BeerDto beerDto;
}
