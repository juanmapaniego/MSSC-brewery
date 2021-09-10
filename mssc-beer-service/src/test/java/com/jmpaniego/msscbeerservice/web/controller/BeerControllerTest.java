package com.jmpaniego.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmpaniego.msscbeerservice.domain.Beer;
import com.jmpaniego.msscbeerservice.services.BeerService;
import com.jmpaniego.msscbeerservice.web.model.BeerDto;
import com.jmpaniego.msscbeerservice.web.model.BeerStyleEnum;
import org.hibernate.type.descriptor.java.OffsetDateTimeJavaDescriptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeer;

    @BeforeEach
    void setUp() {
        validBeer = BeerDto.builder().id(UUID.randomUUID())//id(1L)
                .beerName("Beer1")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .upc("123456789012")
                .price(new BigDecimal("12.95"))
                .build();
    }

    /*@Test
    void getBeerById() throws Exception {
        given(beerService.getBeerById(any())).willReturn(BeerDto.builder().id(validBeer.getId()).build());

        mockMvc.perform(get("/api/v1/beers/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                //.andExpect(jsonPath("$.id",is(validBeer.getId().toString())))
                //.andExpect(jsonPath("$.beerName", is("Beer1")));
    }

    @Test
    void saveNewBeer() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(null);

        BeerDto savedDto = BeerDto.builder().id(1L).beerName("New Beer").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        given(beerService.saveNewBeer(beerDto)).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/beers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        given(beerService.updateBeer(1L, beerDto)).willReturn(beerDto);

        mockMvc.perform(put("/api/v1/beers/1" +
                        "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }*/
}