package com.minju.cafeOrder.user.service;

import com.minju.cafeOrder.user.dto.User;
import com.minju.cafeOrder.user.dto.UserRequest;
import com.minju.cafeOrder.user.dto.UserResponse;

public interface AuthService {
    int signup(UserRequest.UserSignupRequest user);

    UserResponse.UserLoginResponse login(UserRequest.UserLoginRequest request);
}
