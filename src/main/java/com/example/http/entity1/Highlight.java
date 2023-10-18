package com.example.http.entity1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@RequiredArgsConstructor

public class Highlight {
    private List<String> pre_tags;
    private List<String> post_tags;
    private Map<String, Map<String, Object>> fields;
    private int fragment_size;
}
