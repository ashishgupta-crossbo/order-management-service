package com.example.dto.response.hotel;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Introspected
public class AmenityDto {
    private String otaCode;
    private String codeDetail;
    private String desc;
}
