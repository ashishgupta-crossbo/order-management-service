package com.example.exceptions;

import com.example.exception.handler.AbstractException;
import com.example.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class ServerException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ErrorCode errorCode;

    public ServerException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
