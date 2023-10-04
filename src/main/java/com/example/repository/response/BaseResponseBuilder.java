package com.example.repository.response;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
@Introspected
public class BaseResponseBuilder {
    private Map<String, String> data = new HashMap<>();

    public BaseResponseBuilder message(String message) {
        data.put("message", message);
        return this;
    }
}
