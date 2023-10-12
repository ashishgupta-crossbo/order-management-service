package com.example.exception.handler;

import com.example.exceptions.ServerException;
import com.example.dto.response.ApiError;
import com.example.dto.response.BaseResponse;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = { ServerException.class, ExceptionHandler.class })
public class InternalServerErrorHandler implements ExceptionHandler<ServerException, HttpResponse<BaseResponse>>  {

    @Override
    public HttpResponse<BaseResponse> handle(HttpRequest request, ServerException exception) {
        ApiError apiError = new ApiError(1001, "Something went wrong. Please try again in sometime.");
        return HttpResponse.badRequest(new BaseResponse<>(false, null, apiError));
    }
}
