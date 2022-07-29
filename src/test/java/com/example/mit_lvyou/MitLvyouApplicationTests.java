package com.example.mit_lvyou;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mit_lvyou.entity.TravelInfo;
import com.example.mit_lvyou.entity.User;
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id","1");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    void test() {
        TravelInfo user = userMapper.selectUserTravelById(1);
        System.out.println(user);
    }

}
