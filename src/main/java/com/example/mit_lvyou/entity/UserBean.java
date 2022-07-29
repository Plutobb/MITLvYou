package com.example.mit_lvyou.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hongbaozhang
 * @date 2022/7/28 17:09
 */
@Data
@TableName("user_table")
public class UserBean {
    /**
     * 用户id
     **/
    @TableId
    private Integer userId;
    /**
     * 用户名
     **/
    private String phoneNum;
    /**
     * 密码
     **/
    private String password;
    /**
     * 昵称
     **/
    private String nickname;
    /**
     * 性别 0 女 1 男
     **/
    private Integer sex;
    /**
     * 年龄
     **/
    private Integer age;
    /**
     * 性格
     **/
    private String userCharacter;
}
