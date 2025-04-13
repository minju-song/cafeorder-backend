package com.minju.cafeOrder.user.dto;

import com.minju.cafeOrder.core.jwt.CustomUserDetails;

public class UserResponse {
    public record UserLoginResponse(
            String access_token,
            UserClient user
    ) {
        public static UserResponse.UserLoginResponse from(String token, UserClient user) {
            return new UserResponse.UserLoginResponse(
                    token,
                    user
            );
        }
    }

    public record UserClient(
            int id,
            String email,
            String username
    ) {
        public static UserResponse.UserClient from(CustomUserDetails user) {
            return new UserResponse.UserClient(
                    user.getId(),
                    user.getUsername(),
                    user.getName()
            );
        }
    }
}
