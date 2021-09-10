package com.jmpaniego.msscbeerinventoryservice.web.mappers;

import com.jmpaniego.msscbeerinventoryservice.domain.BeerInventory;
import com.jmpaniego.msscbeerinventoryservice.web.model.BeerInventoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * Created by jt on 2019-05-31.
 */
@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface BeerInventoryMapper {
    //@Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "TimestampToOffset")
    //@Mapping(source = "lastModifiedDate",target = "lastModifiedDate",qualifiedByName = "TimestampToOffset")
    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    //@Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "OffsetToTimestamp")
    //@Mapping(source = "lastModifiedDate",target = "lastModifiedDate",qualifiedByName = "OffsetToTimestamp")
    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);

    /*@Named("OffsetToTimestamp")
    public static Timestamp OffsetToTimestamp(OffsetDateTime offset){
        return Timestamp.from(offset.toInstant());
    }

    @Named("TimestampToOffset")
    public static OffsetDateTime TimestampToOffset(Timestamp timestamp){
        return OffsetDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }*/
}
