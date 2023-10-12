package com.example.dto.response.hotel;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class TaxDto {
    private double amount;
    private String taxName;
    private int otaTaxCode;
    private int chargeFrequency;
    private String date;
}
