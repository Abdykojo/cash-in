package com.example.http.service.impl;
import com.example.http.entity1.*;
import com.example.http.service.MydataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MydataServiceImpl implements MydataService {

    @Override
    public Mydata create(String name) {
        Mydata data = new Mydata();
        data.setVersion(true);
        data.setSize(500);

        // Заполнение поля "sort"
        Sort sort = new Sort();
        Map<String, Map<String, String>> timestampSort = Map.of(
                "@timestamp", Map.of("order", "desc", "unmapped_type", "boolean")
        );
        sort.setTimestamp(timestampSort);

        // Заполнение поля "_source"
        Map<String, List<String>> source = Map.of("excludes", List.of());
        data.set_source(source);

        // Заполнение поля "aggs"
        Map<String, Map<String, Map<String, String>>> aggs = Map.of(
                "2", Map.of(
                        "date_histogram", Map.of(
                                "field", "@timestamp",
                                "interval", "10s",
                                "time_zone", "Asia/Yekaterinburg",
                                "min_doc_count", "1"
                        )
                )
        );
        data.setAggs(aggs);

        // Заполнение поля "stored_fields"
        data.setStored_fields(List.of("*"));

        // Заполнение поля "script_fields"
        ScriptFields scriptFields = new ScriptFields();
        data.setScript_fields(scriptFields);

        // Заполнение поля "docvalue_fields"
        DocValueField docValueField = new DocValueField();
        docValueField.setField("@timestamp");
        docValueField.setFormat("date_time");
        data.setDocvalue_fields(List.of(docValueField));

        // Заполнение поля "query"
        Query query = new Query();
        Bool bool = new Bool();
        Map<String, Object> queryStringMap = Map.of(
                "query", "UMAI_136019949",
                "analyze_wildcard", true,
                "default_field", "*"
        );
        bool.setMust(List.of(Map.of("query_string", queryStringMap)));

        Map<String, Object> timestampRangeMap = Map.of(
                "gte", 1694759738914L,
                "lte", 1694760338914L,
                "format", "epoch_millis"
        );
        bool.setMust(List.of(Map.of("range", Map.of("@timestamp", timestampRangeMap))));

        query.setBool(bool);
        data.setQuery(query);

        // Заполнение поля "highlight"
        Highlight highlight = new Highlight();
        highlight.setPre_tags(List.of("@kibana-highlighted-field@"));
        highlight.setPost_tags(List.of("@/kibana-highlighted-field@"));
        highlight.setFields(Map.of("*", Map.of()));
        highlight.setFragment_size(2147483647);
        data.setHighlight(highlight);

        // Заполнение поля "timeout"
        data.setTimeout("30000ms");

        // Теперь объект data содержит все данные из вашего JSON.
        System.out.println(data);

        return data;
    }
}



