package com.adidas.publicservice.exception;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * A key identifying the business error.
     */
    private final MessageKey messageKey;

    /**
     * Additional exception parameters.
     */
    private final List<String> parameters;


    public Message(MessageKey businessErrorKey, Object... args) {
        this.messageKey = businessErrorKey;
        this.parameters = Arrays.stream(args).map(Object::toString).collect(Collectors.toList());
    }

    public String getKey() {
        return messageKey != null ? messageKey.getKey() : "";
    }

    public List<String> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    @Override
    public String toString() {
        return super.toString() + " " + getKey() + " " + parameters;
    }
}