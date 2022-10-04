package com.example.msbeerservice.services.brewing;

import com.example.msbeerservice.config.JmsConfig;
import com.example.msbeerservice.domain.Beer;
import com.example.msbeerservice.events.BrewBeerEvent;
import com.example.msbeerservice.events.NewInventoryEvent;
import com.example.msbeerservice.repositories.BeerRepository;
import com.example.msbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();
        Beer beer = beerRepository.getOne(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
