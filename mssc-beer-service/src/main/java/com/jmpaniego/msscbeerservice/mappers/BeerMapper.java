package com.jmpaniego.msscbeerservice.mappers;

import com.jmpaniego.msscbeerservice.domain.Beer;
import com.jmpaniego.msscbeerservice.web.model.BeerDto;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "TimestampToOffset")
    @Mapping(source = "lastModifiedDate",target = "lastModifiedDate",qualifiedByName = "TimestampToOffset")
    BeerDto beerToBeerDto(Beer beer);

    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "TimestampToOffset")
    @Mapping(source = "lastModifiedDate",target = "lastModifiedDate",qualifiedByName = "TimestampToOffset")
    BeerDto beerToBeerDtoWithInventory(Beer beer);

    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "OffsetToTimestamp")
    @Mapping(source = "lastModifiedDate",target = "lastModifiedDate",qualifiedByName = "OffsetToTimestamp")
    Beer beerDtoToBeer(BeerDto beerDto);

    @Named("OffsetToTimestamp")
    public static Timestamp OffsetToTimestamp(OffsetDateTime offset){
        return Timestamp.from(offset.toInstant());
    }

    @Named("TimestampToOffset")
    public static OffsetDateTime TimestampToOffset(Timestamp timestamp){
        return OffsetDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }
}
