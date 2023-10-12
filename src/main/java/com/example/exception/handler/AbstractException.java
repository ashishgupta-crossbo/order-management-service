package com.example.exception.handler;


import com.example.common.ErrorCode;
import lombok.Getter;

@Getter
public class AbstractException extends RuntimeException {

    private final ErrorCode errorCode;

    public AbstractException(ErrorCode errorCode, Exception cause) {
        super(errorCode.name(), cause);
        this.errorCode = errorCode;
    }

    public AbstractException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

}