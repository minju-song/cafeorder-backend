package com.minju.cafeOrder.user.service;

import com.minju.cafeOrder.user.dto.User;
import com.minju.cafeOrder.user.dto.UserRequest;

public interface UserService {
    User findUserByEmail(String email);
}
