package com.example.mit_lvyou;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mit_lvyou.entity.TravelInfoBean;
import com.example.mit_lvyou.entity.UserBean;
import com.example.mit_lvyou.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MitLvyouApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id","1");
        UserBean userBean = userMapper.selectOne(queryWrapper);
        System.out.println(userBean);
    }

    @Test
    void test() {
        TravelInfoBean user = userMapper.selectUserTravelById(1);
        System.out.println(user);
    }

}
