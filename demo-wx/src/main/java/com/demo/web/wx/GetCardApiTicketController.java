package com.demo.web.wx;

import com.alibaba.fastjson.JSONObject;
import com.demo.core.utils.ResponseUtil;
import com.demo.core.weixin.WxApi;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 获取卡券api调用签名
 *
 * @author hst on 2017/04/28
 **/
@Slf4j
@RestController
public class GetCardApiTicketController {

    private WxApi wxApi;

    @Autowired
    public GetCardApiTicketController(WxApi wxApi) {
        Assert.notNull(wxApi);
        this.wxApi = wxApi;
    }

    @RequestMapping("getCardTicket")
    public String getCardTicket(@RequestParam String cardId, String openId, String code) {
        JSONObject result = new JSONObject();

        result.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        result.put("nonce_str", RandomStringUtils.random(10, true, true));

        List<String> values = new ArrayList<>();
        result.entrySet().forEach((entry) -> {
            values.add(String.valueOf(entry.getValue()));
        });

        values.add(cardId);
        values.add(wxApi.getCardApiTicket());

        if (StringUtils.hasLength(openId)) {
            values.add(openId);
        }
        if (StringUtils.hasLength(code)) {
            values.add(code);
        }

        Collections.sort(values);

        String valueStr = values.stream().reduce("", (r, e) -> r = r + e);
        log.info("cardApi加密字符串：{}", valueStr);

        String signature = DigestUtils.sha1Hex(valueStr);
        result.put("signature", signature);
        log.info("cardApi加密结果：{}", signature);

        return ResponseUtil.successToClient(result);
    }
}
