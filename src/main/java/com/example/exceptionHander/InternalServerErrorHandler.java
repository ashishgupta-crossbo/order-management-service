package com.example.exceptionHander;

import com.example.exceptions.ServerException;
import com.example.repository.common.ErrorCode;
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
@Requires(classes = { ServerException.class, ExceptionHandler.class })
public class InternalServerErrorHandler extends AbstractException implements ExceptionHandler<ServerException, HttpResponse<BaseResponse>>  {

    @Inject
    private final QuoteHelper quoteHelper;

    public InternalServerErrorHandler(ErrorCode errorCode, Exception cause, QuoteHelper quoteHelper) {
        super(errorCode, cause);
        this.quoteHelper = quoteHelper;
    }

    public InternalServerErrorHandler(ErrorCode errorCode, QuoteHelper quoteHelper) {
        super(errorCode);
        this.quoteHelper = quoteHelper;
    }


    @Override
    public HttpResponse<BaseResponse> handle(HttpRequest request, ServerException exception) {
        HttpResponse<BaseResponse> baseResponse=quoteHelper.createErrorResponse();
        return HttpResponse.serverError(baseResponse).
                status(HttpStatus.BAD_REQUEST).body();    }
}
