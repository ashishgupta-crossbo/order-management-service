package com.example.exceptionHander;

import com.example.exceptions.CustomHttpException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
public class CustomHttpExceptionHandler implements ExceptionHandler<CustomHttpException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, CustomHttpException exception) {
        return HttpResponse.badRequest().body("{\"message\": \"" + exception.getMessage() + "\"}");
    }
}