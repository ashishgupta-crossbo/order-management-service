package com.example.dto.request;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
@Builder
public class GetQuotationRequest {
    private long id;
    private String quotationReference;
    private String dateCheckIn;
    private String dateCheckOut;
    private String status;

}
