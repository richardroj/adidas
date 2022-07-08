package com.adidas.publicservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    public static final String DEFAULT = "DEFAULT";

    private String message;
    private String code;
    private UUID uuid;
    
    private Map<String, List<ValidationErrorResponse>> validationErrors;

    public void addValidationMessages(List<Message> messages) {
        messages.forEach(this::addErrorMessage);
    }

    void addErrorMessage(Message message) {
        this.addValidationError(DEFAULT, message.getKey(), message.getParameters());
    }

    private void addValidationError(String path, ValidationErrorResponse subError) {
        if (validationErrors == null) {
            validationErrors = new HashMap<>();
        }
        List<ValidationErrorResponse> validationErrorResponseList = validationErrors.computeIfAbsent(path, k -> new ArrayList<>());
        validationErrorResponseList.add(subError);
    }

    private void addValidationError(String path, String code, List<String> arguments) {
        addValidationError(path, new ValidationErrorResponse(code, arguments));
    }

}