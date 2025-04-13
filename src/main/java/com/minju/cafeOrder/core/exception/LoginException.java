package com.minju.cafeOrder.core.exception;

import org.springframework.http.HttpStatus;

public class LoginException extends RuntimeException{

    public LoginException(String message) {super(message);}

    public HttpStatus status() {return HttpStatus.UNAUTHORIZED; }
}
