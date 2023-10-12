package com.example.validation;

import com.example.common.ErrorCode;
import com.example.exceptions.CustomException;
import com.example.dto.request.QuotationRequest;
import jakarta.inject.Singleton;

@Singleton
public class QuotationValidator {
    public void validateQuotationRequest(QuotationRequest hotelRequest) {
        if (hotelRequest.getRequestRooms().isEmpty()){
            throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
    }
}
