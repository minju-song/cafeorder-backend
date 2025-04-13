package com.minju.cafeOrder.user.mapper;

import com.minju.cafeOrder.user.dto.User;
import com.minju.cafeOrder.user.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // 회원가입용 insert
    int insertUser(User user);

    // 이메일 중복 체크용 (선택)
    int existsByEmail(String email);

    // 회원 찾기
    Optional<User> findUserByEmail(String email);
}
