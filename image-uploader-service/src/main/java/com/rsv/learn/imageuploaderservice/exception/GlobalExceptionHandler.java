package com.rsv.learn.imageuploaderservice.exception;

import com.rsv.learn.imageuploaderservice.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileStorageInternalException.class)
    public ResponseEntity<ResponseError> handle(FileStorageInternalException ex) {
        ResponseError error = getResponseError(ex.getMessage(), ex.getErrorDetails());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(FileStorageClientException.class)
    public ResponseEntity<ResponseError> handle(FileStorageClientException ex) {
        ResponseError error = getResponseError(ex.getMessage(), ex.getErrorDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseError> handle(FileNotFoundException ex) {
        ResponseError error = getResponseError(ex.getMessage(), ex.getErrorDetails());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    private static ResponseError getResponseError(String message, List<String> errorDetails) {
        return ResponseError.builder()
                .message(message)
                .timeStamp(LocalDateTime.now())
                .errorDetails(errorDetails)
                .build();
    }

}
