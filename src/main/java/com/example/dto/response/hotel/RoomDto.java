package com.example.dto.response.hotel;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Introspected
public class RoomDto {
    private String typeCode;
    private String typeName;
    private int maxOccupancy;
    private int numberBeds;
    private int maxRollaways;
    private int otaRoomViewCode;
    private String roomViewName;
    private String shortDesc;
    private String longDesc;
    private List<ImageDto> images;
    private List<AmenityDto> amenities;
}
