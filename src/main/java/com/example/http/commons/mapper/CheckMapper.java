package com.example.http.commons.mapper;
import com.example.http.request.CheckRequest;
import org.mapstruct.Mapper;
import com.example.http.entity.Check;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CheckMapper extends BaseMapper<Check, CheckRequest> {
    CheckMapper INSTANCE = Mappers.getMapper(CheckMapper.class);
}
