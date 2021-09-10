package com.jmpaniego.msscbeerservice.services;

import com.jmpaniego.msscbeerservice.domain.Beer;
import com.jmpaniego.msscbeerservice.web.model.BeerDto;
import com.jmpaniego.msscbeerservice.web.model.BeerPagedList;
import com.jmpaniego.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerPagedList getBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnhand);
    BeerDto getBeerById(UUID beerId, Boolean showInventoryOnhand);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
