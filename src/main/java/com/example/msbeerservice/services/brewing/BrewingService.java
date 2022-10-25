package com.example.msbeerservice.services.brewing;

import com.example.msbeerservice.config.JmsConfig;
import com.example.msbeerservice.domain.Beer;
import com.example.events.BrewBeerEvent;
import com.example.msbeerservice.repositories.BeerRepository;
import com.example.msbeerservice.services.inventory.BeerInventoryService;
import com.example.msbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beerList = beerRepository.findAll();

        beerList.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());
            log.debug("Min onHand is: " + beer.getMinQOnHand());
            log.debug("Inventory is: " + invQOH);

            if (beer.getMinQOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.assembleBeerDto(beer)));
            }
        });
    }
}
