package com.example.msbeerservice.web.controller;

import com.example.msbeerservice.services.BeerService;
import com.example.msbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("{id}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID id) {
        return new ResponseEntity<>(beerService.getBeerById(id), OK);
    }

    @PostMapping
    public ResponseEntity<HttpHeaders> createBeer(@Validated @RequestBody BeerDto beerDto) {
        BeerDto beer = beerService.createBeer(beerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beers/" + beer.getId().toString());

        return new ResponseEntity<>(headers, CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BeerDto> updateBeerById(@PathVariable UUID id, @Validated @RequestBody BeerDto beerDto) {
        var dto = beerService.updateBeer(id, beerDto);
        return new ResponseEntity<>(dto, NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBeer(@PathVariable UUID id) {
        beerService.deleteBeer(id);
    }
}
