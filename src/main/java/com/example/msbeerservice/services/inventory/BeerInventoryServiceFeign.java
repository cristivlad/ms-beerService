package com.example.msbeerservice.services.inventory;

import com.example.msbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("local-discovery")
@Slf4j
@RequiredArgsConstructor
@Service
public class BeerInventoryServiceFeign implements BeerInventoryService{

    private final InventoryServiceFeignClient inventoryServiceFeignClient;
    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling inventory service - beerId: " + beerId);

        ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnHandInventory(beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        log.debug("BeerId: " + beerId + " on hand is: " + onHand);
        return onHand;
    }
}
