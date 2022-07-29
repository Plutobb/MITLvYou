package com.example.mit_lvyou.service;

import com.example.mit_lvyou.entity.TravelInfoBean;
import com.example.mit_lvyou.entity.dto.MatchResponseDTO;
import com.example.mit_lvyou.entity.dto.ResultDTO;
import com.example.mit_lvyou.mapper.TravelMapper;
import com.example.mit_lvyou.mapper.UserMapper;
import com.example.mit_lvyou.util.SimilarityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hongbaozhang
 * @date 2022/7/29 10:38
 */
@Service
public class MatchService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TravelMapper travelMapper;

    public ResultDTO getMatchUsers(TravelInfoBean travelInfo){
        ResultDTO resultDTO = new ResultDTO();
        List<MatchResponseDTO> matchResult;
        //首先对旅行数据进行存储
        if (saveTravelInfo(travelInfo) != null){
            //插入成功进行匹配操作
            List<MatchResponseDTO> allTravelInfo = travelMapper.selectAllUserAndTravelInfo();
            Set<String> userTag =new HashSet<>(Arrays.asList(travelInfo.getTags().split(",")));
            allTravelInfo.forEach(travel -> {
                if (!Objects.equals(travel.getUserId(), travelInfo.getUserId())){
                    Set<String> allTag=new HashSet<>(Arrays.asList(travel.getTags().split(",")));
                    if (!userTag.isEmpty() && !allTag.isEmpty()){
                        ArrayList<Double> userScore = new ArrayList<>();
                        ArrayList<Double> allScore = new ArrayList<>();
                        //以标签长的人作为匹配基准
                        if (userTag.size() > allTag.size()){
                            for (String s : userTag) {
                                if (allTag.contains(s)){
                                    userScore.add(1.0);
                                    allScore.add(1.0);
                                }else {
                                    userScore.add(1.0);
                                    allScore.add(0.0);
                                }
                            }
                        }else {
                            for (String s : allTag) {
                                if (userTag.contains(s)){
                                    userScore.add(1.0);
                                    allScore.add(1.0);
                                }else {
                                    userScore.add(0.0);
                                    allScore.add(1.0);
                                }
                            }
                        }
                        //进行相似度计算
                        double similarity = SimilarityUtil.similarity(userScore, allScore);
                        travel.setSimVal(similarity);
                    }
                }
            });
            //按照相似度排序
            matchResult = allTravelInfo.stream().sorted(Comparator.comparing(MatchResponseDTO::getSimVal)).collect(Collectors.toList());
            resultDTO.setCode(200);
            resultDTO.setMessage("获取匹配信息成功!");
            resultDTO.setData(matchResult);
        }else {
            resultDTO.setCode(500);
            resultDTO.setMessage("获取匹配信息失败!");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    public TravelInfoBean saveTravelInfo(TravelInfoBean travelInfo){
        if (travelMapper.insert(travelInfo) > 0){
            return travelInfo;
        }else {
            System.out.println("插入旅行信息失败");
            return null;
        }
    }
}
