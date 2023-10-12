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
public class CancellationPolicyDto {
    private String policyText;
    private double penaltyAmount;
    private String currencyCode;
    private boolean penaltyInclusiveTax;
    private String policyCode;
    private String absoluteDeadlineDatetime;
    private boolean cancellationAllowed;
}
