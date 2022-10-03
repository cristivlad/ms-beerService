package com.example.msbeerservice.web.controller;

import com.example.msbeerservice.services.BeerService;
import com.example.msbeerservice.web.model.BeerDto;
import com.example.msbeerservice.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static java.util.UUID.randomUUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
//@ComponentScan(basePackages = "com.example.msbeerservice")
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
//        when(beerService.getBeerById(any(), any())).thenReturn(getValidBeerDto());

        given(beerService.getBeerById(any(), anyBoolean())).willReturn(getValidBeerDto());
        mockMvc.perform(get("/api/v1/beer/{beerId}", randomUUID())
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer", pathParameters(
                        parameterWithName("beerId").description("UUID of desired beer to get.")
                )));

    }

    @Test
    void createBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        when(beerService.createBeer(any())).thenReturn(getValidBeerDto());

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        when(beerService.updateBeer(any(), any())).thenReturn(getValidBeerDto());

        mockMvc.perform(put("/api/v1/beer/" + randomUUID())
                .contentType(APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    private BeerDto getValidBeerDto() {
        return BeerDto.builder().beerName("My Beer").beerStyle(BeerStyleEnum.IPA)
                .price(new BigDecimal("2.99"))
                .upc("1241").build();
    }
}