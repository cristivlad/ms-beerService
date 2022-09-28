package com.example.msbeerservice.services.inventory;

import static org.junit.jupiter.api.Assertions.*;

import com.example.msbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled // utility for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {
    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnHandInventory() {

        UUID random = UUID.randomUUID();
        //todo evolve to use UPC
         Integer qoh = beerInventoryService.getOnHandInventory(random);

        System.out.println("UUID: " + random);

        System.out.println(qoh);

    }
}