package com.example.mit_lvyou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mit_lvyou.entity.TravelInfoBean;
import com.example.mit_lvyou.entity.dto.MatchResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hongbaozhang
 * @date 2022/7/29 15:15
 */
@Mapper
public interface TravelMapper extends BaseMapper<TravelInfoBean> {

    @Select("select * from travel_table")
    List<TravelInfoBean> selectAll();

    @Select("select * from travel_table join user_table on travel_table.user_id = user_table.user_id")
    List<MatchResponseDTO> selectAllUserAndTravelInfo();

    @Select("select * from travel_table join user_table on travel_table.user_id = user_table.user_id where to_area = #{toArea}")
    List<MatchResponseDTO> selectAllUserAndTravelInfoByToArea(TravelInfoBean travelInfoBean);
}
