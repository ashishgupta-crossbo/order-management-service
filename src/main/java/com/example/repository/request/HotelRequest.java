package com.example.repository.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@Introspected
@ToString
public class HotelRequest {

    @NotBlank(message = "Hotel code can not be null or empty")
    @JsonProperty("hotelCode")
    private String hotelCode;

    @NotBlank(message = "Quotation Reference can not be null or empty")
    private String quotationReference;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String dateCheckIn;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String dateCheckOut;

    @NotBlank(message = "rate plan code can not be null or empty")
    private String ratePlanCode;

    @NotBlank
    private String totalAmountBeforeTax;

    @NotBlank
    private String totalAmountAfterTax;

    @NotBlank
    private String totalTaxAmount;

    private List<RoomRequest> requestRooms;
}
