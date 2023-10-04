package com.example.exceptionHander;

import com.example.exceptions.RoomNotFoundException;
import com.example.repository.response.BaseResponse;
import com.example.util.QuoteHelper;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = { RoomNotFoundException.class, ExceptionHandler.class })
public class RoomNotFoundExceptionHandler implements ExceptionHandler<RoomNotFoundException, HttpResponse<BaseResponse>> {

    @Inject
    private final QuoteHelper quoteHelper;

    public RoomNotFoundExceptionHandler(QuoteHelper quoteHelper) {
        this.quoteHelper = quoteHelper;
    }

    @Override
    public HttpResponse<BaseResponse> handle(HttpRequest request, RoomNotFoundException exception) {
 HttpResponse<BaseResponse> baseResponse=quoteHelper.createErrorResponse();
        return HttpResponse.serverError(baseResponse).
                status(HttpStatus.BAD_REQUEST).body();
    }
}
