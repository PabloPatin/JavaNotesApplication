package com.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception){
        ApiException ex = new ApiException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(404).body(ex);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> handleNotFoundException(BadRequestException exception){
        ApiException ex = new ApiException(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
    }
}
