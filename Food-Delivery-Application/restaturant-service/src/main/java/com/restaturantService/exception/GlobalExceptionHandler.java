package com.restaturantService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleRestaurant(NotFoundException e) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("error", "error citizen not found ");
        objectMap.put("message", e.getMessage());
        objectMap.put("status", HttpStatus.NOT_FOUND.value());
        objectMap.put("timeStamp", LocalDate.now());
        return new ResponseEntity<>(objectMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestNotFoundException.class)
    public ResponseEntity<Map<String,Object>> badRequest(BadRequestNotFoundException e){
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("error","error badRequest found ");
        objectMap.put("message",e.getMessage());
        objectMap.put("status",HttpStatus.NOT_FOUND.value());
        objectMap.put("timeStamps",LocalDate.now());
        return new ResponseEntity<>(objectMap,HttpStatus.NOT_FOUND);
    }
}
