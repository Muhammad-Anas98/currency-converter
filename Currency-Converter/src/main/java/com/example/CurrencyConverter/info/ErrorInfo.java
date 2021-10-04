package com.example.CurrencyConverter.info;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorInfo {

    private String message;
    @JsonIgnore
    private String[] params;

    public ErrorInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String[] getParams() {
        return params;
    }
}
