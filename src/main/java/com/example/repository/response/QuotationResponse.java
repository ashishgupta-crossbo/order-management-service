package com.example.repository.response;

import com.example.repository.model.RoomBooking;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Serdeable
public class QuotationResponse {
    private String hotelCode;
    private String dateCheckIn;
    private String dateCheckOut;
    private String quotationRef;
    private String ratePlanCode;
    private String totalAmountBeforeTax;
    private String totalAmountAfterTax;
    private String totalTaxAmount;
    private List<RoomBookingDTO> reqRooms;
}
