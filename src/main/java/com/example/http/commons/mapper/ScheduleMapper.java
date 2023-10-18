package com.example.http.commons.mapper;

import com.example.http.entity.Schedule;
import com.example.http.request.ScheduleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule, ScheduleRequest> {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);
}
