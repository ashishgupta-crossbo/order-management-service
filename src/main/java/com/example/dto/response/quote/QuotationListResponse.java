package com.example.dto.response.quote;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@Introspected
@Builder
public class QuotationListResponse {
    private List<QuotationResponse> quotation;
}
