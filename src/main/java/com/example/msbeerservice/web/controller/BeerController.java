package com.example.msbeerservice.web.controller;

import com.example.msbeerservice.web.model.BeerDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    @GetMapping("{id}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID id) {
        // toDo impl
        return new ResponseEntity<>(BeerDto.builder().build(), OK);
    }

    @PostMapping
    public ResponseEntity createBeer(@RequestBody BeerDto beerDto) {
        // toDo impl
        return new ResponseEntity(CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateBeerById(@PathVariable UUID id, @RequestBody BeerDto beerDto) {
        // toDo impl
        return new ResponseEntity(NO_CONTENT);
    }
}
