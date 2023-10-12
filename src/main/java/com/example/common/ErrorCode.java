package com.example.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(120, "Internal Server Error"),
    CUSTOM_EXCEPTION(120,"Something went wrong. Please try again in sometime.");

    private int value;
    private String message;

    ErrorCode(int i, String s) {
        this.value=i;
        this.message=s;
    }
}
