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
public class GuaranteePolicyDto {
    private String policyText;
    private double guaranteeAmount;
    private String currencyCode;
    private String policyCode;
}
