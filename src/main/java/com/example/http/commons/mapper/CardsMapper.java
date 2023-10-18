package com.example.http.commons.mapper;
import com.example.http.entity.Cards;
import com.example.http.request.CardsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CardsMapper extends BaseMapper<Cards, CardsRequest>{
    CardsMapper INSTANCE = Mappers.getMapper(CardsMapper.class);
}
