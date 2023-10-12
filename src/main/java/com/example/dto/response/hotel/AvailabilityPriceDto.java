package com.example.dto.response.hotel;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Introspected
public class AvailabilityPriceDto {
    private String startDate;
    private String endDate;
    private double minPrice;
    private String currencyCode;
    private boolean available;
    private double minPriceBeforeTax;
}
