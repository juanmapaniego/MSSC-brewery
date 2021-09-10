package com.jmpaniego.msscbeerservice.services;

import com.jmpaniego.msscbeerservice.domain.Beer;
import com.jmpaniego.msscbeerservice.exceptions.NotFoundException;
import com.jmpaniego.msscbeerservice.mappers.BeerMapper;
import com.jmpaniego.msscbeerservice.repositories.BeerRepository;
import com.jmpaniego.msscbeerservice.web.model.BeerDto;
import com.jmpaniego.msscbeerservice.web.model.BeerPagedList;
import com.jmpaniego.msscbeerservice.web.model.BeerStyleEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    public BeerServiceImpl(BeerRepository beerRepository, BeerMapper beerMapper) {
        this.beerRepository = beerRepository;
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerPagedList getBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnhand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        System.out.println("pageRequest = " + pageRequest);
        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle.toString())){
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle.toString(), pageRequest);
        }else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle.toString())) {
            //search by beer name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        }else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle.toString())) {
            //search by beer style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        }else {
            beerPage = beerRepository.findAll(pageRequest);
        }
        //System.out.println("beerPage = " + beerPage);
        //System.out.println("beerPage = " );
        //beerPage.getContent().forEach(System.out::println);

        if(showInventoryOnhand){
            beerPagedList = new BeerPagedList(beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory).collect(Collectors.toList())
                    , PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageNumber())
                    , beerPage.getTotalElements());
        }else{
            beerPagedList = new BeerPagedList(beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList())
                    , PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageNumber())
                    , beerPage.getTotalElements());
        }
        System.out.println("beerPagedList = " + beerPagedList);
        beerPagedList.getContent().forEach(System.out::println);
        return beerPagedList;
    }

    @Override
    public BeerDto getBeerById(UUID beerId, Boolean showInventoryOnhand) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Beer " + beerId + " not found"));
        BeerDto beerDto;

        if(showInventoryOnhand){
            beerDto = beerMapper.beerToBeerDtoWithInventory(beer);
        }else{
            beerDto = beerMapper.beerToBeerDto(beer);
        }
        return beerDto;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer save = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return beerMapper.beerToBeerDto(save);
    }

    @Override
        public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(() -> new NotFoundException("Beer " + beerId + " not found"));

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
