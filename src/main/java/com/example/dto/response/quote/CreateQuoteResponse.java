package com.example.dto.response.quote;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class CreateQuoteResponse {

    private String data;
    private long hotelId;
}
