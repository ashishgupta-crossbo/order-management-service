package com.example.dto.response.quote;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
@Introspected
@Serdeable
public class RoomBookingDTO {
    private String roomTypeCode;
    private String ratePlanCode;
    private String adultCount;
    private String childrenCount;
    private String promoCode;
}
