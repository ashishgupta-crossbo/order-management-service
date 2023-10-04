package com.example.repository.response;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Introspected
@Serdeable
public class ApiError {
    private int code;
    private String message;
}

