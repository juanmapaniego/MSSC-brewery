package com.jmpaniego.msscbeerservice.bootstrap;

import com.jmpaniego.msscbeerservice.domain.Beer;
import com.jmpaniego.msscbeerservice.repositories.BeerRepository;
import com.jmpaniego.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepository.count() == 0){
            beerRepository.save(Beer.builder()
                        .beerName("Mango Bobs")
                        //.beerStyle(BeerStyleEnum.PALE_ALE)
                        .beerStyle("PALE_ALE")
                        .upc(BEER_1_UPC)
                        .quantityToBrew(200)
                        .price(new BigDecimal("12.95"))
                        .minOnHand(12)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    //.beerStyle(BeerStyleEnum.IPA)
                    .beerStyle("IPA")
                    .upc(BEER_2_UPC)
                    .quantityToBrew(200)
                    .price(new BigDecimal("11.95"))
                    .minOnHand(12)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("No Hammers On The Bar")
                    //.beerStyle(BeerStyleEnum.PALE_ALE)
                    .beerStyle("PALE_ALE")
                    .upc(BEER_3_UPC)
                    .quantityToBrew(200)
                    .price(new BigDecimal("10.95"))
                    .minOnHand(12)
                    .build());
        }
    }
}
