package com.demo.core.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;

/**
 * Created by 胡超云 on 2016/12/5.
 * 高德地图工具类
 */
public class AMapUtil {

    //高德地图API-废弃
    //private static String aMap = "http://restapi.amap.com/v3/geocode/geo?output=JSON&key=06683f945f98f25ce97682d152e9e5b4&address=";
    //腾讯地图API
    private static String map = "http://apis.map.qq.com/ws/geocoder/v1/?key=EVKBZ-M4G6F-375JG-JZQL2-7DG2F-NKFNC&address=";
    //获取当前位置
    private static String address = "http://apis.map.qq.com/ws/geocoder/v1/?key=EVKBZ-M4G6F-375JG-JZQL2-7DG2F-NKFNC&get_poi=1&location={0},{1}";

    public static String getLocationAddress(double lng, double lat) {

        String url = MessageFormat.format(address, lat, lng);

        String realAddress = null;
        String realCity = null;
        JSONObject json = new JSONObject();

        try {
            //接口异常，循环三次
            for (int n = 1; n <= 3; n++) {
                JSONObject result = JSONObject.parseObject(HttpUtil.doGet(url));
                if ("0".equals(result.getString("status"))) {
                    realAddress = result.getJSONObject("result").getString("address");
                    realCity = result.getJSONObject("result").getJSONObject("address_component").getString("city");
                    json.put("address", realAddress);
                    json.put("city", realCity);
                    if (!StringUtils.isEmpty(realAddress)) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    /**
     * 返回经纬度
     *
     * @param addr
     * @return
     */
    public static JSONObject getLngLat(String addr) {
        final String url = map + addr;

        JSONObject location = null;

        try {
            //接口异常，循环三次
            for (int n = 1; n <= 3; n++) {
                JSONObject result = JSONObject.parseObject(HttpUtil.doGet(url));
                if ("0".equals(result.getString("status"))) {
                    location = result.getJSONObject("result").getJSONObject("location");
                    if (location != null && StringUtils.hasText(location.getString("lng"))) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public static JSONObject getLocationComponent(double lat, double lng) {
        String url = MessageFormat.format(address, lat, lng);

        try {
            //接口异常，循环三次
            for (int n = 1; n <= 3; n++) {
                JSONObject result = JSONObject.parseObject(HttpUtil.doGet(url));
                if ("0".equals(result.getString("status"))) {
                    return result.getJSONObject("result").getJSONObject("address_component");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 计算两点之间的直线距离
     *
     * @param lng1
     * @param dim1
     * @param lng2
     * @param dim2
     * @return 千米
     */
    public static double getDistanceKM(double lng1, double dim1, double lng2, double dim2) {
        double lon1 = (Math.PI / 180) * lng1;
        double lon2 = (Math.PI / 180) * lng2;
        double lat1 = (Math.PI / 180) * dim1;
        double lat2 = (Math.PI / 180) * dim2;

        // 地球半径
        double R = 6371;

        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
        return new BigDecimal(d).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 计算两点之间的直线距离
     *
     * @param lng1
     * @param dim1
     * @param lng2
     * @param dim2
     * @return 米
     */
    public static int getDistanceM(double lng1, double dim1, double lng2, double dim2) {
        return (int) getDistanceKM(lng1, dim1, lng2, dim2) * 1000;
    }
}
