package com.minju.cafeOrder.core.exception;

import org.springframework.http.HttpStatus;

public class Exception401 extends RuntimeException{

    public Exception401(String message) {super(message);}

    public HttpStatus status() {return HttpStatus.UNAUTHORIZED; }
}
