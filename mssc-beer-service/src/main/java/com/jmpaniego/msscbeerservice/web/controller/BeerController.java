package com.jmpaniego.msscbeerservice.web.controller;


import com.jmpaniego.msscbeerservice.domain.Beer;
import com.jmpaniego.msscbeerservice.services.BeerService;
import com.jmpaniego.msscbeerservice.web.model.BeerDto;
import com.jmpaniego.msscbeerservice.web.model.BeerPagedList;
import com.jmpaniego.msscbeerservice.web.model.BeerStyleEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerPagedList> getBeersList(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                      @RequestParam(value = "beerName", required = false) String beerName,
                                                      @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                      @RequestParam(value = "showInventoryOnhand", required = false) Boolean showInventoryOnhand){
        if(showInventoryOnhand == null){
            showInventoryOnhand = false;
        }

        if (pageNumber == null || pageNumber <= 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if(beerStyle == null){
            beerStyle = BeerStyleEnum.EMPTY;
        }
        if (beerName == null){
            beerName = "";
        }

        BeerPagedList beerList = beerService.getBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnhand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @Operation(summary = "Get beer by id",description = "Get one beer", tags = {"Beer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = BeerDto.class)))
    })
    @Parameters(value = {
            @Parameter(name = "beerId", description = "The id of the searched beer")
    })
    @GetMapping(value = "/{beerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId, @RequestParam(value = "showInventoryOnhand", required = false) Boolean showInventoryOnhand){
        //return ResponseEntity.ok(BeerDto.builder().build());
        if(showInventoryOnhand == null){
            showInventoryOnhand = false;
        }
        System.out.println("beerId = " + beerId);
        BeerDto beerById = beerService.getBeerById(beerId, showInventoryOnhand);

        return ResponseEntity.ok(beerById);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto){
        return new ResponseEntity(beerService.saveNewBeer(beerDto),HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto){
        return new ResponseEntity(beerService.updateBeer(beerId,beerDto),HttpStatus.NO_CONTENT);
    }
}
