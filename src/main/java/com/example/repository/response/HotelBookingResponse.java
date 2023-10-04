package com.example.repository.response;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class HotelBookingResponse {
    private long id;
    private String dateCheckIn;
    private String dateCheckOut;
    private String status;
    private String reference;
    private List<RoomBookingDTO> bookingResponses;
    private BaseResponse baseResponse;


    public HotelBookingResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
}
