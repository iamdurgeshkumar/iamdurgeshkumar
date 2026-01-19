package com.deliveryService.exception;

public class BadRequestFoundException extends RuntimeException{
    public  BadRequestFoundException (String message) {
        super(message);
    }
}
