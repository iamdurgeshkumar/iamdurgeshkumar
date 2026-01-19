package com.restaturantService.exception;

public class BadRequestNotFoundException extends RuntimeException{
    public BadRequestNotFoundException(String message) {
        super(message);
    }
}
