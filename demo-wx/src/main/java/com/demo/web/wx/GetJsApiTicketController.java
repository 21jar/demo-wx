package com.demo.web.wx;

import com.alibaba.fastjson.JSONObject;
import com.demo.core.utils.ResponseUtil;
import com.demo.core.utils.UuidUtil;
import com.demo.core.weixin.WxApi;
import com.demo.env.WxProperties;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by 胡超云 on 2017/2/11.
 * 获取jsapi_ticket
 */
@Slf4j
@Validated
@RestController
public class GetJsApiTicketController {

    @Autowired
    private WxApi wxApi;

    @Autowired
    private WxProperties wxProperties;

    @GetMapping("getSign.do")
    public String getSign(String url) {

        String ticket = wxApi.getJsapiTicket();
        String nonceStr = UuidUtil.getUUID();
        long timestamp = System.currentTimeMillis() / 1000;

        url = url.contains("#") ? url.substring(0, url.indexOf("#")) : url;
        String str = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;

        String signature = DigestUtils.sha1Hex(str);

        JSONObject jb = new JSONObject();
        jb.put("appId", wxProperties.getAppId());
        jb.put("timestamp", timestamp);
        jb.put("nonceStr", nonceStr);
        jb.put("signature", signature);
        return ResponseUtil.successToClient(jb);
    }

    @RequestMapping("monitorJsSign.do")
    public void monitorJsSign(String error, String configUrl, String configMessage) {
        log.info("============ 微信jsSdkConfig错误：{}, configUrl: {}, configMessage: {}, ticket: {} =================", error, configUrl, configMessage, wxApi.getJsapiTicket());
    }

}
