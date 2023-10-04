package com.example.exceptions;

import com.example.exceptionHander.AbstractException;
import com.example.repository.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class RoomNotFoundException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 1L;

    public RoomNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RoomNotFoundException(ErrorCode errorCode, Exception cause) {
        super(errorCode, cause);
    }}
