package com.example.mit_lvyou.entity.dto;

import lombok.Data;

/**
 * @author hongbaozhang
 * @date 2022/7/29 15:08
 */
@Data
public class MatchRequestDTO {
    /**
     * 用户id
     **/
    private Integer userId;
    /**
     * 出发地
     **/
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
     * 目的地偏好标签
     **/
    private String tags;
}
