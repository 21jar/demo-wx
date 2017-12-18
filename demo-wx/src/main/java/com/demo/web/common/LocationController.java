package com.demo.web.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.utils.AMapUtil;
import com.demo.core.utils.ResponseUtil;

/**
 * 定位控制器
 *
 * @author hst on 2017/05/15
 */
@RestController
@RequestMapping("location/")
public class LocationController {

    @RequestMapping("geocoder")
    public String geoCoder(@RequestParam Double lat, @RequestParam Double lng) {
        return ResponseUtil.successToClient(AMapUtil.getLocationComponent(lat, lng));
    }
}
