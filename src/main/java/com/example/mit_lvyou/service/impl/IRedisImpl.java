package com.example.mit_lvyou.service.impl;

import com.example.mit_lvyou.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author hongbaozhang
 * @date 2022/8/3 11:02
 */
@Service
public class IRedisImpl implements IRedisService {

    private final String GEO_KEY = "city";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Long addGeo(Map<String, Point> map) {
        return redisTemplate.boundGeoOps(GEO_KEY).add(map);
    }

    @Override
    public List<String> getRadius(String cityName) {
        Metric metric = RedisGeoCommands.DistanceUnit.KILOMETERS;
        Distance distance = new Distance(1000, metric);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands
                .GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending();
        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = redisTemplate.opsForGeo().radius(GEO_KEY, cityName, distance, args);
        LinkedList<String> nearCities = new LinkedList<>();
        if (radius != null) {
            radius.forEach(geoLocationGeoResult -> {
                RedisGeoCommands.GeoLocation<String> content = geoLocationGeoResult.getContent();
                //member 名称  如  tianjin
                String name = content.getName();
                // 对应的经纬度坐标
                Point pos = content.getPoint();
                // 距离中心点的距离
                Distance dis = geoLocationGeoResult.getDistance();
                nearCities.add(content.getName());
            });
        }
        return nearCities;
    }

    @Override
    public List<Point> getCityPoint(String cityName) {
        return redisTemplate.opsForGeo().position(GEO_KEY, cityName);
    }

    public void cleanCityPointInfo(String cityName){
        redisTemplate.boundZSetOps(GEO_KEY).remove(cityName);
    }

    public void cleanRedis(){
        redisTemplate.delete(GEO_KEY);
    }
}
