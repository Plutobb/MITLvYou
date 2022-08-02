package com.example.mit_lvyou.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSignReqDTO {
    private String username;
    private String password;
    private String phoneNum;
    private int age;
    private List<String> characters;
    // 30天内免登录
    private Boolean remeberUserInfo;
}
