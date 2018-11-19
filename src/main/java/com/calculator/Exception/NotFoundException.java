package com.calculator.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;


@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String message, IOException exception) {
        super(message, exception);
    }
}

