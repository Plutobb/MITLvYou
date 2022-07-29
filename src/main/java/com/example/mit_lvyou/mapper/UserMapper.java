package com.example.mit_lvyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mit_lvyou.entity.TravelInfoBean;
import com.example.mit_lvyou.entity.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hongbaozhang
 * @date 2022/7/29 11:13
 */
@Mapper
public interface UserMapper extends BaseMapper<UserBean> {
    @Select("select * from user_table as user join travel_table as travel on user.user_id = travel.userid where user.user_id = #{userId}")
    TravelInfoBean selectUserTravelById(Integer userId);
}
