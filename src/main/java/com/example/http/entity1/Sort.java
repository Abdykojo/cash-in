package com.example.http.entity1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@RequiredArgsConstructor

public class Sort {
    private Map<String, Map<String, String>> timestamp;
}
