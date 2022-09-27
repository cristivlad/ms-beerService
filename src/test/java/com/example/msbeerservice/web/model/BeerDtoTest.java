package com.example.msbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class BeerDtoTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        var dto = getBeerDto();

        String jsonString = objectMapper.writeValueAsString(dto);

        assertNotNull(jsonString);
    }

    @Test
    void testDeserialize() throws JsonProcessingException {
        String jsonText = "{\"id\":null,\"version\":null,\"createdDate\":null,\"lastModifiedDate\":null,\"beerName\":\"My Beer\",\"beerStyle\":\"IPA\",\"upc\":1241,\"price\":2.99,\"quantityOnHand\":null}";

        var dto = objectMapper.readValue(jsonText, BeerDto.class);

        assertNotNull(dto);
        assertEquals("IPA", dto.getBeerStyle().name());
    }
}