package com.minju.cafeOrder.user.service;

import com.minju.cafeOrder.user.dto.User;
import com.minju.cafeOrder.user.dto.UserRequest;
import com.minju.cafeOrder.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByEmail(String email) {
        User user = userMapper.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + email));

        return user;
    }
}
