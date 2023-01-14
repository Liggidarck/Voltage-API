package com.george.server.voltage.voltage.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoltageError {
    private int statusCode;
    private String message;

    public VoltageError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
