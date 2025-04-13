package com.minju.cafeOrder.user.dto;


public class UserRequest {

    public record UserSignupRequest(
            String email,
            String username,
            String password
    ) {
        public static UserSignupRequest from(User user) {
            return new UserSignupRequest(
                    user.getEmail(),
                    user.getUsername(),
                    user.getPassword()
            );
        }
    }

    public record UserLoginRequest(
            String email,
            String password
    ) {
        public static UserLoginRequest from(User user) {
            return new UserLoginRequest(
                    user.getEmail(),
                    user.getPassword()
            );
        }
    }
}
