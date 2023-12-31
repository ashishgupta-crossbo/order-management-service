package com.example.exceptions;

import com.example.exception.handler.AbstractException;
import com.example.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class CustomException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CustomException(ErrorCode errorCode, Exception cause) {
        super(errorCode, cause);
    }}
