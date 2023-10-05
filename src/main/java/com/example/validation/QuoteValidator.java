package com.example.validation;

import com.example.repository.common.ErrorCode;
import com.example.exceptions.CustomException;
import com.example.repository.request.CreateQuoteRequest;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;

@Singleton
public class QuoteValidator {
    public void validateHotelRequest(CreateQuoteRequest hotelRequest) {
        if (hotelRequest.getRequestRooms().isEmpty()){
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }
}
