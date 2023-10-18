package com.example.http.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private String code;
    private String message;
    private UUID qid;
    private String dts;
    private String name;

    private CheckRespint checkRespint;

}
