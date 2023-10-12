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
public class NightlyRateDto {
    private String date;
    private double fee;
    private double price;
    private double tax;
    private double total;
}
