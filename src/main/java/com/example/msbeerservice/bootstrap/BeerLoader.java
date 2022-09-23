package com.example.msbeerservice.bootstrap;

import com.example.msbeerservice.domain.Beer;
import com.example.msbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
            loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minQOnHand(12)
                    .upc(37252L)
                    .price(new BigDecimal("12.95"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("Pale Ale")
                    .quantityToBrew(200)
                    .minQOnHand(12)
                    .upc(23553222L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }
    }
}
