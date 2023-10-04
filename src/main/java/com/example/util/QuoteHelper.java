package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.repository.response.ApiError;
import com.example.repository.response.BaseResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import jakarta.inject.Singleton;

import java.util.HashMap;

@Singleton
public class QuoteHelper {

    public HttpResponse<BaseResponse> createErrorResponse() {
        ApiError apiError = new ApiError(1001, "Something went wrong. Please try again in sometime.");
        return HttpResponse.badRequest(new BaseResponse<>(false, null, apiError));
    }

    public HttpResponse<BaseResponse> createSuccessResponse(Object data) {
        return HttpResponse.ok(new BaseResponse<>(true, data, null));
    }

    public HttpResponse<BaseResponse> createSuccessResponseForUpdate() {
        return HttpResponse.ok(new BaseResponse<>(true, null, null));
    }
}
