package com.example.mit_lvyou.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author hongbaozhang
 * @date 2022/7/29 14:28
 */
@Data
public class MatchResponseDTO {
    /**
     * 用户id
     **/
    private Integer userId;
    /**
     * 用户名
     **/
    private String phoneNum;
    /**
     * 昵称
     **/
    private String nickname;
    /**
     * 性别 0 女 1 男
     **/
    private Integer sex;
    /**
     * 年龄
     **/
    private Integer age;
    /**
     * 性格
     **/
    private String userCharacter;
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
     * 目的地兴趣偏好标签
     **/
    private String tags;
    /**
     * 匹配度
     **/
    private double simVal;
}
