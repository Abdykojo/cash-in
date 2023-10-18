package com.example.http.commons.mapper;
import com.example.http.entity.Credit;
import com.example.http.request.CreditRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditsMapper extends BaseMapper<Credit, CreditRequest>{
    CreditsMapper INSTANCE = Mappers.getMapper(CreditsMapper.class);
}
