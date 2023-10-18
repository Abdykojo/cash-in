package com.example.http.entity1;

import lombok.*;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mydata {
    private boolean version;
    private int size;
    private List<Sort> sort;
    private Map<String, List<String>> _source;
    private Map<String, Map<String, Map<String, String>>> aggs;
    private List<String> stored_fields;
    private ScriptFields script_fields;
    private List<DocValueField> docvalue_fields;
    private Query query;
    private Highlight highlight;
    private String timeout;
}
