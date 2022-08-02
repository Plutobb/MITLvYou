package com.example.mit_lvyou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Objects;

/**
 * @author hongbaozhang
 * @date 2022/7/28 17:23
 */
@Data
@TableName("travel_table")
public class TravelInfoBean {
    /**
     * 旅行id
     **/
    @TableId(type = IdType.AUTO)
    private String travelId;
    /**
     * 出发地
     **/
    private String fromArea;
    /**
     * 目的地
     **/
    private String toArea;
    /**
     * 出行时间
     **/
    private Integer time;
    /**
     * 出行人数
     **/
    private Integer numPeople;
    /**
     * 可接受人均消费金额
     **/
    private Integer acceptAmount;
    /**
     * 兴趣偏好标签
     **/
    private String tags;
    /**
     * 旅行信息的用户Id
     **/
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelInfoBean that = (TravelInfoBean) o;
        return Objects.equals(fromArea, that.fromArea) && Objects.equals(toArea, that.toArea) && Objects.equals(time, that.time) && Objects.equals(numPeople, that.numPeople) && Objects.equals(acceptAmount, that.acceptAmount) && Objects.equals(tags, that.tags) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromArea, toArea, time, numPeople, acceptAmount, tags, userId);
    }
}
