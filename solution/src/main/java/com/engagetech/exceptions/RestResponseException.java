package com.engagetech.exceptions;

public class RestResponseException extends RuntimeException {
    public RestResponseException(String message){
        super(message);
    }
}
