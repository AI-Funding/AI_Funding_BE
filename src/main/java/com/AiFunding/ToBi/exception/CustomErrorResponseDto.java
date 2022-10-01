package com.AiFunding.ToBi.exception;

import lombok.Getter;

@Getter
public class CustomErrorResponseDto {
    private final int errorCode;
    private final String errorMessage;

    public CustomErrorResponseDto(ErrorCodes errorCodes) {
        this.errorCode = errorCodes.getErrorCode();
        this.errorMessage = errorCodes.getErrorMessage();
    }
}
