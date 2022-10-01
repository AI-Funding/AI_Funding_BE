package com.AiFunding.ToBi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(CustomError.class)
    public ResponseEntity<CustomErrorResponseDto> handle(CustomError e) {
        return new ResponseEntity<>(new CustomErrorResponseDto(e.getErrorCodes()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
