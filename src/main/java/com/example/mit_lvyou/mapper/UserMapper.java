package com.example.mit_lvyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mit_lvyou.entity.TravelInfo;
import com.example.mit_lvyou.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hongbaozhang
 * @date 2022/7/29 11:13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user_table as user join travel_table as travel on user.user_id = travel.userid where user.user_id = #{userId}")
    TravelInfo selectUserTravelById(Integer userId);
}
