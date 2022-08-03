package com.example.mit_lvyou;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mit_lvyou.entity.TravelInfoBean;
import com.example.mit_lvyou.entity.UserBean;
import com.example.mit_lvyou.mapper.TravelMapper;
import com.example.mit_lvyou.mapper.UserMapper;
import com.example.mit_lvyou.service.impl.IRedisImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MitLvyouApplicationTests {

    @Autowired
    IRedisImpl iRedis;

    @Autowired
    private TravelMapper travelMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id","1");
        UserBean userBean = userMapper.selectOne(queryWrapper);
        System.out.println(userBean);
    }

    @Test
    void test() {
        TravelInfoBean user = userMapper.selectUserTravelById(1);
        System.out.println(user);
    }

    @Test
    void test1() {
        List<String> tianjin = iRedis.getRadius("郑州");
//        iRedis.getCityPoint("上海");
    }

    @Test
    void testInsert() {
        TravelInfoBean travelInfoBean = new TravelInfoBean();
        travelInfoBean.setFromArea("北京");
        travelInfoBean.setToArea("上海");
        travelInfoBean.setAcceptAmount(3000);
        travelInfoBean.setTags("海边,沙滩");
        travelInfoBean.setTime(3);
        travelInfoBean.setNumPeople(2);
        travelInfoBean.setUserId(1);
        int insert = travelMapper.insert(travelInfoBean);
        System.out.println(travelInfoBean.getTravelId());
    }

    @Test
    void cleanRedis(){
//        iRedis.cleanCityPointInfo("shijiazhuang");
        iRedis.cleanRedis();
    }

    @Test
    void addInfo(){
        String cityInfo = "北京经纬度:(116.41667,39.91667)" +
                "上海经纬度:(121.43333,34.50000)" +
                "天津经纬度:(117.20000,39.13333)" +
                "香港经纬度:(114.10000,22.20000)" +
                "广州经纬度:(113.23333,23.16667)" +
                "珠海经纬度:(113.51667,22.30000)" +
                "深圳经纬度:(114.06667,22.61667)" +
                "杭州经纬度:(120.20000,30.26667)" +
                "重庆经纬度:(106.45000, 29.56667)" +
                "青岛经纬度:(120.33333,36.06667)" +
                "厦门经纬度:(118.10000,24.46667)" +
                "福州经纬度:(119.30000,26.08333)" +
                "兰州经纬度:(103.73333,36.03333)" +
                "贵阳经纬度:(106.71667,26.56667)" +
                "长沙经纬度:(113.00000,28.21667)" +
                "南京经纬度:(118.78333,32.05000)" +
                "南昌经纬度:(115.90000,28.68333)" +
                "沈阳经纬度:(123.38333,41.80000)" +
                "太原经纬度:(112.53333,37.86667)" +
                "成都经纬度:(104.06667,30.66667)" +
                "拉萨经纬度:(91.00000,29.60000)" +
                "乌鲁木齐经纬度:(87.68333,43.76667)" +
                "昆明经纬度:(102.73333,25.05000)" +
                "西安经纬度:(108.95000,34.26667)" +
                "西宁经纬度:(101.75000,36.56667)" +
                "银川经纬度:(106.26667,38.46667)" +
                "兰浩特经纬度:(122.08333,46.06667)" +
                "哈尔滨经纬度:(126.63333,45.75000)" +
                "武汉经纬度:(114.31667,30.51667)" +
                "郑州经纬度:(113.65000,34.76667)" +
                "石家庄经纬度:(114.48333,38.03333)" +
                "三亚经纬度:(109.50000,18.20000)" +
                "海口经纬度:(110.35000,20.01667)" +
                "澳门经纬度:(113.50000,22.20000)";
        Map<String, Point> points = new HashMap<>();
        String[] split = cityInfo.split("\\)");
        for (String s : split) {
            String[] city = s.split("经纬度:\\(");
            String cityName = city[0];
            String[] split1 = city[1].split(",");
            Point point = new Point(Double.parseDouble(split1[0]),Double.parseDouble(split1[1]));
            points.put(cityName,point);
        }
        iRedis.addGeo(points);
    }

}
