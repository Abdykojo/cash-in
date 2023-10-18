package com.example.http.commons.mapper;
import com.example.http.entity.StatusCheck;
import com.example.http.request.CheckStatusRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CheckStatusMapper extends BaseMapper<StatusCheck, CheckStatusRequest> {
    CheckStatusMapper INSTANCE = Mappers.getMapper(CheckStatusMapper.class);
}
