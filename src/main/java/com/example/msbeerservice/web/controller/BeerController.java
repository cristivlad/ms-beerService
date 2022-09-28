package com.example.msbeerservice.web.controller;

import com.example.msbeerservice.services.BeerService;
import com.example.msbeerservice.web.model.BeerDto;
import com.example.msbeerservice.web.model.BeerPagedList;
import com.example.msbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping("{id}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID id) {
        return new ResponseEntity<>(beerService.getBeerById(id), OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> createBeer(@Validated @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.createBeer(beerDto), CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BeerDto> updateBeerById(@PathVariable UUID id, @Validated @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.updateBeer(id, beerDto), NO_CONTENT);
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<BeerPagedList> listBeer(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                  @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                  @RequestParam(value = "beerName", required = false) String beerName,
                                                  @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(beerList, OK);
    }
}
