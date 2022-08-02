package com.example.mit_lvyou.controller;

import com.example.mit_lvyou.entity.UserBean;
import com.example.mit_lvyou.entity.dto.EditUserInfoDTO;
import com.example.mit_lvyou.entity.dto.ResultDTO;
import com.example.mit_lvyou.entity.dto.UserSignReqDTO;
import com.example.mit_lvyou.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hongbaozhang
 * @date 2022/7/29 11:02
 */
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getAllUser")
    @ResponseBody
    public ResultDTO getAllUser(){
        List<UserBean> allUser = userService.getAllUser();
        ResultDTO resultDTO = new ResultDTO();
        if (allUser!=null && allUser.size()>0){
            resultDTO.setCode(200);
            resultDTO.setMessage("获取用户信息成功!");
            resultDTO.setData(allUser);
        }else{
            resultDTO.setCode(500);
            resultDTO.setMessage("获取用户信息失败!");
        }
        return resultDTO;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultDTO signIn(@RequestBody EditUserInfoDTO userInfo){
        ResultDTO resultDTO = new ResultDTO();
        UserBean user = userService.signIn(userInfo.getUsername(),userInfo.getPassword());
        if (user != null){
            resultDTO.setCode(200);
            resultDTO.setMessage("登录成功");
            resultDTO.setData(user);
        }else {
            resultDTO.setCode(500);
        }
        return resultDTO;
    }


    //用户注册
    @PostMapping("/signup")
    @ResponseBody
    public ResultDTO signUp(@RequestBody EditUserInfoDTO userInfo){
        ResultDTO resultDTO = new ResultDTO();
        UserBean user = userService.signUp(userInfo.getUsername(),userInfo.getPassword());

        if (user != null ){
            resultDTO.setCode(200);
            resultDTO.setMessage("注册成功");
            resultDTO.setData(user);
        }else {
            resultDTO.setCode(500);
            resultDTO.setMessage("注册失败,用户名已存在");
        }

        return resultDTO;
    }

    // 用户完善信息
    @PostMapping("/edit")
    @ResponseBody
    public ResultDTO editUserInfo(@RequestBody EditUserInfoDTO userInfo){
        Long userId = Long.parseLong(userInfo.getId());
        userInfo.setUserId(userId);
        ResultDTO resultDTO = new ResultDTO();
        UserBean userBean = userService.updateUserInfo(userInfo);
        if (userBean!=null){
            resultDTO.setCode(200);
            resultDTO.setMessage("更新成功");
            resultDTO.setData(userBean);
        }else {
            resultDTO.setCode(500);
            resultDTO.setMessage("更新失败");
        }
        return resultDTO;
    }

}
