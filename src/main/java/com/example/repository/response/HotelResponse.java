package com.example.repository.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
@Introspected
@Builder
public class HotelResponse {
    @JsonProperty("quotation_id")
    private long quotationId;
}
