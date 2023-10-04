package com.example.exceptionHander;


import com.example.repository.common.ErrorCode;

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
    public ErrorCode getErrorCode() {
        return errorCode;
    }

}