package com.example.mit_lvyou.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class EditUserInfoDTO {
    private String id;
    private String name;
    private String sex;
    private int age;
    private List<String> characters;
    private String password;
    private String phonenum;
    private String username;
    private Long userId;
}
