package com.minju.cafeOrder.aop;

import com.minju.cafeOrder.core.api.ApiResponse;
import com.minju.cafeOrder.core.api.ResponseService;
import com.minju.cafeOrder.core.exception.Exception401;
import com.minju.cafeOrder.core.exception.LoginException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ResponseService responseService;

    public ApiResponseAdvice(ResponseService responseService) {
        this.responseService = responseService;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @ExceptionHandler(LoginException.class)
    public Object handleLoginException(Exception ex) {
        return responseService.getLoginException(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Object handleException(Exception ex) {
        // 여기서 원하는 에러 응답 구성
        return responseService.getInternalServerError("서버 오류");
    }

    @ExceptionHandler(Exception401.class)
    public Object handleException401(Exception ex) {
        // 여기서 원하는 에러 응답 구성
        return responseService.getBadRequestResult("권한이 없습니다.");
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public Object handleMyBatisSystemException(Exception ex) {
        // 여기서 원하는 에러 응답 구성
        return responseService.getNotFoundResult("데이터 생성 실패");
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse) {
            return body; // 이미 ApiResponse인 경우 그대로 반환
        }

        if (body instanceof Resource) {
            return body;
        }


        // 요청 메서드 확인
        String method = request.getMethod().name();

        // POST 요청인 경우
        if ("POST".equalsIgnoreCase(method)) {
            return responseService.getCreatedResult(body);
        }

        // GET 요청 등 다른 경우
        return responseService.getSuccessResult(body);
    }
}
