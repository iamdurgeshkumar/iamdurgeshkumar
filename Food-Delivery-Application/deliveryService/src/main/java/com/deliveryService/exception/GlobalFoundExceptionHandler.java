package com.deliveryService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalFoundExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleException(NotFoundException e) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", "not found ");
        errorMap.put("message", e.getMessage());
        errorMap.put("status", HttpStatus.NOT_FOUND.value());
        errorMap.put("TimeStamps", LocalDate.now());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestFoundException.class)
    public ResponseEntity<Map<String, Object>> manageException(BadRequestFoundException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Bad request found");
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("TimeStamps", LocalDate.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
