package com.example.mit_lvyou.service;

import com.example.mit_lvyou.entity.TravelInfoBean;
import com.example.mit_lvyou.entity.dto.MatchResponseDTO;
import com.example.mit_lvyou.entity.dto.ResultDTO;
import com.example.mit_lvyou.mapper.TravelMapper;
import com.example.mit_lvyou.mapper.UserMapper;
import com.example.mit_lvyou.service.impl.IRedisImpl;
import com.example.mit_lvyou.util.SimilarityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hongbaozhang
 * @date 2022/7/29 10:38
 */
@Service
public class MatchService {

    @Autowired
    IRedisImpl iRedis;

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
            if (travelInfo.getToArea().isEmpty() && travelInfo.getTags().isEmpty()){
                //用户没有填入匹配信息，直接返回附近城市的用户
                TravelInfoBean travelInfoBean = new TravelInfoBean();
                List<String> radius = iRedis.getRadius(travelInfo.getFromArea());
                if (radius.size() > 1){
                    travelInfoBean.setToArea(radius.get(1));
                }
                resultDTO.setCode(201);
                resultDTO.setMessage("由于你没有填写匹配信息,为你推荐附近热门城市的旅友");
                matchResult = travelMapper.selectAllUserAndTravelInfoByToArea(travelInfoBean);
            }else {
                List<MatchResponseDTO> allTravelInfo;
                if (travelInfo.getToArea().isEmpty()){
                    allTravelInfo = travelMapper.selectAllUserAndTravelInfo();
                }else {
                    allTravelInfo = travelMapper.selectAllUserAndTravelInfoByToArea(travelInfo);
                }
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
                matchResult = allTravelInfo.stream().filter(travelInfos-> !Objects.equals(travelInfos.getUserId(), travelInfo.getUserId())).sorted(Comparator.comparing(MatchResponseDTO::getSimVal)).collect(Collectors.toList());
                resultDTO.setCode(200);
                if (matchResult.size() == 0){
                    resultDTO.setCode(202);
                    resultDTO.setMessage("没有匹配到合适的旅友,为你推荐以下旅友");
                    matchResult = travelMapper.selectAllUserAndTravelInfo();
                }else {
                    resultDTO.setMessage("匹配旅友成功!");
                }
            }
            resultDTO.setData(matchResult);
        }else {
            resultDTO.setCode(500);
            resultDTO.setMessage("获取匹配信息失败!");
            resultDTO.setData(null);
        }
        return resultDTO;
    }

    public TravelInfoBean saveTravelInfo(TravelInfoBean travelInfo){
        HashSet<TravelInfoBean> set = new HashSet<>(travelMapper.selectAll());
        if (!set.contains(travelInfo)){
            travelMapper.insert(travelInfo);
        }
        return travelInfo;
    }
}
