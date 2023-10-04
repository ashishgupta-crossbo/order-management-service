package com.example.repository.request;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
public class RoomRequest {

    @NotBlank(message = "Room type code can not be null or empty")
    private String roomTypeCode;

    @NotBlank(message = "rate plan code can not be empty or null")
    private String ratePlanCode;

    @Pattern(regexp = "\\d+")
    private String adultCode;

    @Pattern(regexp = "\\d+")
    private String childrenCount;

    @NotBlank(message = "promo code can not be empty or null")
    private String promoCode;
}
