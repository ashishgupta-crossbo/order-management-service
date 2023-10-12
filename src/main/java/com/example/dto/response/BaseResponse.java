package com.example.dto.response;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Data
@Setter
@Getter
@Introspected
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
    public BaseResponse() {
        this.success = true;
        this.data = null;
        this.error = null;
    }

    public BaseResponse(T hotelResponse) {
        this.success = true;
        this.data = hotelResponse;
        this.error = null;
    }
}
