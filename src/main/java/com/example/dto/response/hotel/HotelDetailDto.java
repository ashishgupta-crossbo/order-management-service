package com.example.dto.response.hotel;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class HotelDetailDto {
    private List<HotelResponseDto> hotelsDetail;
}
