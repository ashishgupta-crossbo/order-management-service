package com.example.repository.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class GetQuotationResponse {

    @JsonProperty("quotation_id")
    private long id;

    @JsonProperty("date_check_in")
    private String dateCheckIn;

    @JsonProperty("date_check_out")
    private String dateCheckOut;

    @JsonProperty("status")
    private String status;

    @JsonProperty("quotation_ref")
    private String reference;

}
