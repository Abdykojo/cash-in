package com.example.http.commons.mapper;
import com.example.http.entity.Pay;
import com.example.http.request.PayRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayMapper extends BaseMapper<Pay, PayRequest>{
    PayMapper INSTANCE = Mappers.getMapper(PayMapper.class);
}
