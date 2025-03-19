package com.example.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExceptionResponse {

    @JsonProperty(value = "errorMessage")
    private String errorMessage;

    @JsonProperty(value = "code")
    private int code;

}
