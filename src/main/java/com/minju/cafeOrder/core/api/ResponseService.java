package com.minju.cafeOrder.core.api;


import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ResponseService {

    public enum CommonResponse {
        SUCCESS(200, "성공하였습니다."),
        CREATED(201, "리소스가 성공적으로 생성되었습니다."),
        //        FAIL(400, "실패하였습니다."),
        BAD_REQUEST(400, "잘못된 요청입니다."),
        UNAUTHORIZED(401, "인증이 필요합니다."),
        FORBIDDEN(403, "권한이 없습니다."),
        NOT_FOUND(404, "리소스를 찾을 수 없습니다."),
        ID_ALREADY_EXIST(461, "이미 존재하는 아이디입니다."),
        VALID_JOIN(400, "잘못된 형식입니다."),
        INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),

        //토큰 에러
        UNKNOWN_TOKEN(1001, "토큰이 존재하지 않습니다."),
        EXPIRED_TOKEN(1002, "만료된 토큰입니다."),
        LOGIN_EXCEPTION(1003, "잘못된 로그인입니다.");

        private final int code;
        private final String msg;

        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public <T> ApiResponse<T> getSuccessResult(T data) {
        return new ApiResponse<>(CommonResponse.SUCCESS.getCode(), CommonResponse.SUCCESS.getMsg(), data);
    }

    public <T> ApiResponse<T> getCreatedResult(T data) {
        return new ApiResponse<>(CommonResponse.CREATED.getCode(), CommonResponse.CREATED.getMsg(), data);
    }

    public ApiResponse<?> getBadRequestResult(String message) {
        if(message == null) message = CommonResponse.BAD_REQUEST.getMsg();
        return new ApiResponse<>(CommonResponse.BAD_REQUEST.getCode(), message);
    }

    public ApiResponse<?> getNotFoundResult(String message) {
        return new ApiResponse<>(CommonResponse.NOT_FOUND.getCode(), message);
    }

    public ApiResponse<?> getUnauthorizedResult(String message) {
        return new ApiResponse<>(CommonResponse.UNAUTHORIZED.getCode(), message, null);
    }

    public ApiResponse<?> getForbiddenResult() {
        return new ApiResponse<>(CommonResponse.FORBIDDEN.getCode(), CommonResponse.FORBIDDEN.getMsg(), null);
    }

    public ApiResponse<?> getIdAlreadyExistResult(String message) {
        return new ApiResponse<>(CommonResponse.ID_ALREADY_EXIST.getCode(), message);
    }

    public ApiResponse<?> getValidationJoin(String message, Map<String, String> errors) {
        return new ApiResponse<>(CommonResponse.VALID_JOIN.getCode(), message, errors);
    }

    public ApiResponse<?> getInternalServerError(String message) {
        return new ApiResponse<>(CommonResponse.INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public ApiResponse<?> getLoginException(String message) {
        return new ApiResponse<>(CommonResponse.LOGIN_EXCEPTION.getCode(), message);
    }

    // 토큰 관련
    public <T> ApiResponse<T> getUnknownToken(T data) {
        return new ApiResponse<>(CommonResponse.UNKNOWN_TOKEN.getCode(), CommonResponse.UNKNOWN_TOKEN.getMsg(), data);
    }

    public <T> ApiResponse<T> getExpiredToken(T data) {
        return new ApiResponse<>(CommonResponse.EXPIRED_TOKEN.getCode(), CommonResponse.EXPIRED_TOKEN.getMsg(), data);
    }
}


