package com.adidas.subscriptionservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationErrorResponse {

    private String code;
    private List<String> arguments;

    public ValidationErrorResponse(String code) {
        this.code = code;
    }

}