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
public class RoomResponseDto {
    private List<RoomDto> rooms;

}
