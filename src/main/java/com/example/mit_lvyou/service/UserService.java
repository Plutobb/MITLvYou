package com.example.mit_lvyou.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mit_lvyou.entity.UserBean;
import com.example.mit_lvyou.entity.dto.EditUserInfoDTO;
import com.example.mit_lvyou.entity.dto.UserSignReqDTO;
import com.example.mit_lvyou.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<UserBean> getAllUser(){
        return userMapper.selectAllUser();
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public UserBean signIn(String username,String password) {
        UserBean user = userMapper.selectUserByNickName(username);
        if (user != null && user.getPassword().equals(password)){
            return user;
        }else {
            return null;
        }
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    public UserBean signUp(String username, String password) {
        UserBean userBean = userMapper.selectUserByNickName(username);
        if (userBean != null){
            return null;
        }else{
            UserBean user = new UserBean();
            user.setNickname(username);
            user.setPassword(password);
            userMapper.insert(user);
        }
        return userMapper.selectUserByNickName(username);
    }

    public UserBean updateUserInfo(EditUserInfoDTO userInfo) {
        UserBean user = userMapper.selectById(userInfo.getUserId());
        user.setAge(userInfo.getAge());
        int sex = "男".equals(userInfo.getSex()) ? 0 : 1;
        user.setSex(sex);
        user.setPhoneNum(userInfo.getPhonenum());
        StringBuffer sb = new StringBuffer();
        List<String> characters = userInfo.getCharacters();
        if (characters!=null && characters.size()>0){
            for (int i = 0; i < characters.size(); i++) {
                sb.append(characters.get(i));
                if (i!=(characters.size()-1)){
                    sb.append(";");
                }
            }

        }
        if (sb.length()>0){
            user.setUserCharacter(sb.toString());
        }
        userMapper.updateById(user);
        return userMapper.selectById(user.getUserId());
    }
}
