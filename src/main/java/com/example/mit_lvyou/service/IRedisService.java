package com.example.mit_lvyou.service;

import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Map;

/**
 * @author hongbaozhang
 * @date 2022/8/3 11:01
 */
public interface IRedisService {

    Long addGeo(Map<String, Point> map);

    List<String> getRadius(String cityName);

    List<Point> getCityPoint(String cityName);
}
