package com.example.repository.common;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(120, "Internal Server Error"),
    ROOM_NOT_FOUND_EXCEPTION(120,"Room not found");

    private int value;
    private String message;

    ErrorCode(int i, String s) {
        this.value=i;
        this.message=s;
    }
}
