package com.minju.cafeOrder.user.service;

import com.minju.cafeOrder.core.exception.LoginException;
import com.minju.cafeOrder.core.jwt.CustomUserDetails;
import com.minju.cafeOrder.core.jwt.JwtProvider;
import com.minju.cafeOrder.user.dto.User;
import com.minju.cafeOrder.user.dto.UserRequest;
import com.minju.cafeOrder.user.dto.UserResponse;
import com.minju.cafeOrder.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public int signup(UserRequest.UserSignupRequest request) {

        String hashedPassword = passwordEncoder.encode(request.password());

        if(userMapper.existsByEmail(request.email()) > 0) {
            return -1;
        }

        User user = User.builder()
                .email(request.email())
                .password(hashedPassword) // 암호화된 비번 저장
                .username(request.username())
                .build();

        int result = userMapper.insertUser(user);

        return result;
    }

    @Override
    public UserResponse.UserLoginResponse login(UserRequest.UserLoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String accessToken = jwtProvider.createAccessToken(userDetails.getUsername());
            UserResponse.UserClient user = UserResponse.UserClient.from(userDetails);

                return UserResponse.UserLoginResponse.from(accessToken,user );
            } catch (BadCredentialsException e) {
                throw new LoginException("아이디 또는 비밀번호가 잘못되었습니다.");
            } catch (UsernameNotFoundException e) {
                throw new LoginException("존재하지 않는 사용자입니다.");
            } catch (Exception e) {
            System.out.println(e);
                throw new LoginException("로그인 실패");
        }
    }
}
