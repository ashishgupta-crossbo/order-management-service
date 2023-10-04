package com.example.exceptions;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class CustomHttpException extends HttpStatusException {
    public CustomHttpException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
