package com.example.mit_lvyou;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mit_lvyou.entity.TravelInfoBean;
import com.example.mit_lvyou.entity.UserBean;
import com.example.mit_lvyou.mapper.TravelMapper;
import com.example.mit_lvyou.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MitLvyouApplicationTests {

    @Autowired
    private TravelMapper travelMapper;

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

    @Test
    void testInsert() {
        TravelInfoBean travelInfoBean = new TravelInfoBean();
        travelInfoBean.setFromArea("北京");
        travelInfoBean.setToArea("上海");
        travelInfoBean.setAcceptAmount(3000);
        travelInfoBean.setTags("海边,沙滩");
        travelInfoBean.setTime(3);
        travelInfoBean.setNumPeople(2);
        travelInfoBean.setUserId(1);
        int insert = travelMapper.insert(travelInfoBean);
        System.out.println(travelInfoBean.getTravelId());
    }

}
