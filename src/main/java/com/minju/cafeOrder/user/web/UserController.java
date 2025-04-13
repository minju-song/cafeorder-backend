package com.minju.cafeOrder.user.web;

import com.minju.cafeOrder.core.api.ResponseService;
import com.minju.cafeOrder.user.dto.User;
import com.minju.cafeOrder.user.dto.UserRequest;
import com.minju.cafeOrder.user.dto.UserResponse;
import com.minju.cafeOrder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ResponseService responseService;

//    @PostMapping("/signup")
//    public Object signup(@RequestBody UserRequest.UserSignupRequest request) {
//        int result = userService.signup(request);
//        if(result == -1) {
//            return responseService.getIdAlreadyExistResult("중복되는 아이디가 존재합니다.");
//        }
//        else if(result > 0) {
//            return responseService.getCreatedResult("회원가입 성공");
//        }
//        return responseService.getBadRequestResult("회원가입 실패");
//    }


}
