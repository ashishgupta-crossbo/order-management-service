package com.example.dto.response.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Serdeable
@Introspected
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuotationResponseDto {
    @JsonProperty("quotation_id")
    private long quotationId;
}
