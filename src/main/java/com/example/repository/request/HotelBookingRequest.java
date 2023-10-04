package com.example.repository.request;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@Builder
public class HotelBookingRequest {
    private long id;
    private String quotationReference;
    private String dateCheckIn;
    private String dateCheckOut;
    private String status;

}
