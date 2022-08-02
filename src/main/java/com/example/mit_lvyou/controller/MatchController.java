package com.example.mit_lvyou.controller;

import com.example.mit_lvyou.entity.TravelInfoBean;
import com.example.mit_lvyou.entity.dto.ResultDTO;
import com.example.mit_lvyou.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author hongbaozhang
 * @date 2022/7/29 14:51
 */
@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "测试成功!";
    }

    @CrossOrigin
    @RequestMapping("/getMatch")
    @ResponseBody
    public ResultDTO getMatchInfo(@RequestBody TravelInfoBean travelInfo){
        return matchService.getMatchUsers(travelInfo);
    }
}
