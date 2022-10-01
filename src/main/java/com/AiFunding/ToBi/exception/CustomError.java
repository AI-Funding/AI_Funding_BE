package com.AiFunding.ToBi.exception;

import lombok.Getter;

@Getter
public class CustomError extends RuntimeException {
    private final ErrorCodes errorCodes;

    public CustomError(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }
}
