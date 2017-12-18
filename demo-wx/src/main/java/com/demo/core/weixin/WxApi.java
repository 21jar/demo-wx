package com.demo.core.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.core.utils.RedisUtil;
import com.demo.core.weixin.pojo.WxMsgValidParams;
import com.demo.core.weixin.tool.ResultCheck;
import com.demo.core.weixin.wxobj.*;
import com.demo.core.weixin.wxobj.result.*;
import com.demo.env.WxProperties;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author by hst on 2016/11/30.
 */
@Slf4j
@Component
public class WxApi {

    @Autowired
    private WxProperties wxProperties;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RestTemplate restTemplate;

    synchronized private AccessToken refreshAccessToken() {
        try {
            Object accessTokenObj = redisUtil.get(wxProperties.getAppId());
            if (Objects.isNull(accessTokenObj)) {
                AccessToken accessToken = restTemplate.getForObject(wxProperties.getApiUrl().getAccessTokenUrl(), AccessToken.class);
                if (ResultCheck.isSuccess(accessToken)) {
                    accessToken.setLastRefreshTime(System.currentTimeMillis());
                    redisUtil.set(wxProperties.getAppId(), JSON.toJSONString(accessToken), AccessToken.EXPIRE_TIME);
                } else {
                    throw new Exception(accessToken.toString());
                }
                return accessToken;
            } else {
                return JSON.parseObject(String.valueOf(accessTokenObj), AccessToken.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取accessToken失败：" + e.getMessage());
            return null;
        }
    }

    /*
    * 获取accessToken
    * */
    public AccessToken accessToken() {
        Object accessTokenObj = redisUtil.get(wxProperties.getAppId());
        if (Objects.nonNull(accessTokenObj)) {
            return JSON.parseObject(String.valueOf(accessTokenObj), AccessToken.class);
        }
        return refreshAccessToken();
    }

    /**
     * 删除微信菜单
     */
    public String deleteWxMenu() {
        String deleteUrl = MessageFormat.format(wxProperties.getApiUrl().getMenuDeleteUrl(), accessToken().getAccessToken());
        return restTemplate.getForObject(deleteUrl, String.class);
    }


    /*
    * 发送模板消息
    * */
    public <T> T sendTemplateMsg(TemplateMessage message, Class<T> c) {
        String sendUrl = MessageFormat.format(wxProperties.getApiUrl().getSendTemplateMsgUrl(), accessToken().getAccessToken());
        return restTemplate.postForObject(sendUrl, message, c);
    }

    /*
    * 微信授权
    * */
    public OAuth2AccessToken getOpenId(String code) {
        String acUrl = MessageFormat.format(wxProperties.getApiUrl().getAuthAccessTokenUrl(), code);
        String result = restTemplate.getForObject(acUrl, String.class);
        return JSON.parseObject(result, OAuth2AccessToken.class);
    }

    /*
    * 获取用户信息
    * */
    public OpenUser getUserInfo(String openId) {
        String userUrl = MessageFormat.format(wxProperties.getApiUrl().getUserInfoUrl(), accessToken().getAccessToken(), openId);
        return restTemplate.getForObject(userUrl, OpenUser.class);
    }

    /*
    * 创建汽车卡
    * */
    public CarCardCreateResult createCarCard(CarCard carCard) {
        String carUrl = MessageFormat.format(wxProperties.getApiUrl().getCarCardCreateUrl(), accessToken().getAccessToken());
        return restTemplate.postForObject(carUrl, JSONObject.toJSON(carCard).toString(), CarCardCreateResult.class);
    }

    /*
    * 更新汽车卡
    * */
    public BaseResult updateCarCard(JSONObject carCard) {
        String carUrl = MessageFormat.format(wxProperties.getApiUrl().getCarCardUpdateUrl(), accessToken().getAccessToken());
        return restTemplate.postForObject(carUrl, carCard.toString(), BaseResult.class);
    }

    /*
    * 修改汽车卡库存量
    * */
    public BaseResult modifyStock(JSONObject stock) {
        String modifyUrl = MessageFormat.format(wxProperties.getApiUrl().getModifyStockUrl(), accessToken().getAccessToken());
        return restTemplate.postForObject(modifyUrl, stock.toString(), BaseResult.class);
    }

    /*
    * 解密汽车卡code
    * */
    public CodeDecryptResult decryptCode(String encryptCode) {
        String carUrl = MessageFormat.format(wxProperties.getApiUrl().getCarCardCodeDecryptUrl(), accessToken().getAccessToken());
        JSONObject postData = new JSONObject();
        postData.put("encrypt_code", encryptCode);
        return restTemplate.postForObject(carUrl, postData, CodeDecryptResult.class);
    }

    /*
    * 激活汽车卡
    * */
    public BaseResult activateCard(CarCardActivate active) {
        String activeUrl = MessageFormat.format(wxProperties.getApiUrl().getCarCardActiveUrl(), accessToken().getAccessToken());
        return restTemplate.postForObject(activeUrl, JSONObject.toJSON(active).toString(), BaseResult.class);
    }

    /*
    * 更新个人领取汽车卡信息
    * */
    public BaseResult updateCard(CardUpdateUser updateUser) {
        String updateUrl = MessageFormat.format(wxProperties.getApiUrl().getCardUpdateUserUrl(), accessToken().getAccessToken());
        return restTemplate.postForObject(updateUrl, JSONObject.toJSON(updateUser).toString(), BaseResult.class);
    }

    /*
    * 批量获取用户详细信息列表
    * */
    public OpenUserList getUserInfoList(BatchUserInfoObj userObj) {
        String userUrl = MessageFormat.format(wxProperties.getApiUrl().getBatchUserInfoUrl(), accessToken().getAccessToken());
        String result = restTemplate.postForObject(userUrl, userObj, String.class);
        try {
            return JSONObject.parseObject(result, OpenUserList.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("批量获取用户粉丝信息失败：{}, api结果：{}", e.getMessage(), result);
            return null;
        }
    }

    /*
    * 批量获取用户openId列表
    * */
    public OpenIdList getUserOpenIdList(String startOpenId) {
        String userUrl = MessageFormat.format(wxProperties.getApiUrl().getOpenIdListUrl(), accessToken().getAccessToken(), Objects.isNull(startOpenId) ? "" : startOpenId);
        String result = restTemplate.getForObject(userUrl, String.class);
        try {
            OpenIdList list = JSONObject.parseObject(result, OpenIdList.class);
            list.setOpenId(list.getData().get("openid"));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户粉丝openId列表失败：{}, api结果：{}", e.getMessage(), result);
            return null;
        }
    }

    /*
    * 创建公众号菜单
    * */
    public CreateMenuResult createMenu(Menu menu) {
        String menuUrl;
        if (menu instanceof ConditionMenu) {
            menuUrl = MessageFormat.format(wxProperties.getApiUrl().getMenuCreateConditionalUrl(), accessToken().getAccessToken());
        } else {
            menuUrl = MessageFormat.format(wxProperties.getApiUrl().getMenuCreateUrl(), accessToken().getAccessToken());
        }
        return restTemplate.postForObject(menuUrl, menu, CreateMenuResult.class);
    }

    /*
    * 更新公众号用户组人员
    * */
    public BaseResult groupMemberUpdate(String openId, String groupId) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("openid", openId);
        requestBody.put("to_groupid", groupId);
        return restTemplate.postForObject(MessageFormat.format(wxProperties.getApiUrl().getGroupMemberUpdate(), accessToken().getAccessToken()), requestBody, BaseResult.class);
    }

    /*
    * 创建带参二维码
    * */
    public QrCodeResult createQrCode(QrCode qrCode) {
        return restTemplate.postForObject(MessageFormat.format(wxProperties.getApiUrl().getCreateQrCode(), accessToken().getAccessToken()), qrCode, QrCodeResult.class);
    }

    /*
    * 公众号群发接口
    * */
    public MassSendResult mpNewsMassSend(MpNewsMassSend mpNewsMassSend) {
        return restTemplate.postForObject(MessageFormat.format(wxProperties.getApiUrl().getMessageMassSendUrl(), accessToken().getAccessToken()), mpNewsMassSend, MassSendResult.class);
    }

    /**
     * 获取 jsapi_ticket
     */
    public String getJsapiTicket() {
        //从redis获取jsapi_ticket
        String key = wxProperties.getAppId() + ".js.api.ticket";
        String ticket = (String) redisUtil.get(key);

        if (!StringUtils.hasText(ticket)) {
            //获取accessToken
            ApiTicket result = restTemplate.getForObject(MessageFormat.format(wxProperties.getApiUrl().getJsApiTicketUrl(), accessToken().getAccessToken()), ApiTicket.class);
            ticket = result.getTicket();
            if (ResultCheck.isSuccess(result)) {
                redisUtil.set(key, ticket, AccessToken.EXPIRE_TIME);
            } else {
                log.error("获取jsApiTicket失败：{}", result);
            }
        }
        return ticket;
    }

    /**
     * 获取 card_api_ticket
     */
    public String getCardApiTicket() {
        //从redis获取card_api_ticket
        String key = wxProperties.getAppId() + ".card.api.ticket";
        String ticket = (String) redisUtil.get(key);

        if (!StringUtils.hasText(ticket)) {
            ApiTicket result = restTemplate.getForObject(MessageFormat.format(wxProperties.getApiUrl().getCardApiTicketUrl(), accessToken().getAccessToken()), ApiTicket.class);
            ticket = result.getTicket();
            if (ResultCheck.isSuccess(result)) {
                redisUtil.set(key, ticket, AccessToken.EXPIRE_TIME);
            } else {
                log.error("获取cardApiTicket失败：{}", result);
            }
        }
        return ticket;
    }

    /**
     * 验证公众号签名有效性
     *
     * @param signature 原加密标志
     * @param timestamp 加密时间戳
     * @param nonce     加密随机字符串
     * @return 是否是微信消息
     */
    public boolean signature(String signature, String timestamp, String nonce) {
        if (timestamp == null || nonce == null)
            return false;

        List<String> list = Arrays.asList(timestamp, wxProperties.getToken(), nonce);
        //1.字典排序
        Collections.sort(list);
        //2.sha1加密
        String str = DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));
        //3.判断是否微信信息
        return signature.equals(str);
    }

    public boolean signature(WxMsgValidParams validParams) {
        return signature(validParams.getSignature(), validParams.getTimestamp(), validParams.getNonce());
    }
}
