package com.example.http.commons.mapper;

import com.example.http.entity.Chart;
import com.example.http.request.ChartRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ChartMapper extends BaseMapper <Chart, ChartRequest> {
    ChartMapper INSTANCE = Mappers.getMapper(ChartMapper.class);
}
