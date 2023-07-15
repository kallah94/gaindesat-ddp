package com.gaindesat.ddp.dto;

import org.springframework.http.HttpStatus;

public final class CustomMessage {
    private int statusCode;
    private String message;

    public CustomMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
