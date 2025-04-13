package com.minju.cafeOrder.aop;

import com.minju.cafeOrder.core.api.ApiResponse;
import com.minju.cafeOrder.core.api.ResponseService;
import com.minju.cafeOrder.core.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseService responseService;

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ApiResponse<?>> handleLoginException(LoginException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(responseService.getUnauthorizedResult(e.getMessage()));
    }

    // 예외 공통 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleOtherException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(500, e.getMessage(), null));
    }
}

