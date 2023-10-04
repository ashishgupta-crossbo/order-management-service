package com.example.repository.response;

import com.example.repository.request.HotelRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;
import java.util.Map;

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

    public BaseResponse(boolean success, List<HotelBookingResponse> data, ApiError error) {
        this.success = success;
        this.data = (T) data;
        this.error = error;
    }

    public BaseResponse(HotelResponse hotelResponse) {
        this.success = success;
        this.data = (T) hotelResponse;
        this.error = error;
    }

    public BaseResponse(Map<String, String> errorResponseMap) {
        this.data= (T) errorResponseMap;
    }

    public BaseResponse(HotelBookingResponse hotelBookingResponse) {
        this.success = success;
        this.data = (T) hotelBookingResponse;
        this.error = error;
    }
}
