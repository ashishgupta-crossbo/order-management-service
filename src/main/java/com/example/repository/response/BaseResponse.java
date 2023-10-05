package com.example.repository.response;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@Introspected
@NoArgsConstructor
@Serdeable
@Builder
public class BaseResponse<T> {
    private boolean success;
    private T data;
    private ApiError error;

    public BaseResponse(boolean success, T data, ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public BaseResponse(boolean success, List<GetQuotationResponse> data, ApiError error) {
        this.success = success;
        this.data = (T) data;
        this.error = error;
    }

    public BaseResponse(CreateQuoteResponseDto hotelResponse) {
        this.success = success;
        this.data = (T) hotelResponse;
        this.error = error;
    }
    public BaseResponse(QuotationResponse hotelResponse) {
        this.error = null;
        this.success = true;
        this.data = (T) hotelResponse;
    }
}
