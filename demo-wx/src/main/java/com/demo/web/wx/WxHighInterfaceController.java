package com.demo.web.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.utils.RedisUtil;
import com.demo.core.utils.ResponseUtil;
import com.demo.core.weixin.wxobj.result.MassSendResult;
import com.demo.service.wx.WxFansService;

import java.util.List;
import java.util.Objects;

/**
 * 微信高级群发接口
 *
 * @author hst on 2017/03/07
 **/
@RestController
@RequestMapping("wx/high/")
public class WxHighInterfaceController {

    @Autowired
    private WxFansService wxFansService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据openId发送图文列表
     *
     * @param totalSend 发送总数
     * @param userOffset 用户发送起始排序位置
     * @param perSendSize 每次群发的用户数 最大10000
     * @param mediaId 发送的图文
     */
    @RequestMapping("mpNewsMassSendUser")
    public String mpNewsMassSendUser(@RequestParam int totalSend, @RequestParam int userOffset, @RequestParam int perSendSize, @RequestParam String mediaId) {
        return sendMass(totalSend, userOffset, perSendSize, mediaId, "fans");
    }

    /**
     * 根据openId发送图文列表
     *
     * @param totalSend 发送总数
     * @param userOffset 用户发送起始排序位置
     * @param perSendSize 每次群发的用户数 最大10000
     * @param mediaId 发送的图文
     */
    @RequestMapping("mpNewsMassSendOpenId")
    public String mpNewsMassSendOpenId(@RequestParam int totalSend, @RequestParam int userOffset, @RequestParam int perSendSize, @RequestParam String mediaId) {
        return sendMass(totalSend, userOffset, perSendSize, mediaId, "openId");
    }

    private String sendMass(int totalSend, int userOffset, int perSendSize, String mediaId, String type) {

        if (userOffset < 0 || StringUtils.isEmpty(mediaId)) {
            return ResponseUtil.errorToClient((Object) "userOffset必须为正，mediaId不能为空！");
        } else if (totalSend < 2 || perSendSize < 2 || perSendSize > 10000) {
            return ResponseUtil.errorToClient((Object) "totalSend发送总量>2, 2< perSendSize单次发送用户数必 <= 10000！");
        } else if (Objects.nonNull(isSend())) {
            return ResponseUtil.errorToClient((Object) "正在发送！");
        }
        List<MassSendResult> resultList = wxFansService.mpNewsMassSend(totalSend, userOffset, perSendSize, mediaId, type);
        redisUtil.remove(WxHighInterfaceController.class.getName() + ".lock");
        return ResponseUtil.successToClient(resultList);
    }

    private String isSend() {
        //判断是否开始拉取
        synchronized (WxHighInterfaceController.class) {
            String message = (String) redisUtil.get(WxHighInterfaceController.class.getName() + ".lock");
            if (Objects.nonNull(message)) {
                return message;
            } else {
                redisUtil.set(WxHighInterfaceController.class.getName() + ".lock", "lock");
                return null;
            }
        }
    }
}
