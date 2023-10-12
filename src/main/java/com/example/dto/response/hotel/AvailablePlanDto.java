package com.example.dto.response.hotel;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class AvailablePlanDto {
    private String ratePlanCode;
    private double totalAmountBeforeTax;
    private double totalAmountAfterTax;
    private double totalTaxAmount;
    private CancellationPolicyDto cancellationPolicy;
    private GuaranteePolicyDto guaranteePolicy;
    private List<TaxDto> taxes;
    private List<NightlyRateDto> nightlyRates;
    private String currencyCode;
    private String ratePlanName;
    private String ratePlanDesc;
    private List<PlanFeatureDto> planFeatures;
    private boolean breakfastIncluded;
    private boolean lunchIncluded;
    private boolean dinnerIncluded;
    private int mealPlanCode;
    private String promoCode;
    private String noOfUnitsAvailable;
    private String maxOccupancy;
}
