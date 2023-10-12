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
public class HotelAvailabilityDataDto {
    private List<HotelAvailabilityPriceDto> availabilityPrice;

}
