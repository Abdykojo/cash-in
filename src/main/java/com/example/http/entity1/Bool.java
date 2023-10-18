package com.example.http.entity1;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class Bool {
    private List<Map<String, Object>> must;
    private List<Map<String, Object>> filter;
    private List<Map<String, Object>> should;
    private List<Map<String, Object>> must_not;
}
