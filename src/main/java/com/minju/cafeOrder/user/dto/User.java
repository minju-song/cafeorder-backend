package com.minju.cafeOrder.user.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    int id;
    String username;
    String password;
    String email;
    Date created_at;

}
