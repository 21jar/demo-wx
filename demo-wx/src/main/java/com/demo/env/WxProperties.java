package com.demo.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信api信息常量
 *
 * @author hst on 2016/11/30
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    private String appId;
    //密钥
    private String appSecret;
    //微信验证token
    private String token;
    //微信推送消息加密密钥
    private String encodingAesKey;
    //管理员分组
    private String storeMangerGroupId;
    //默认分组
    private String defaultGroupId;
    //微信接口地址对象
    private ApiUrl apiUrl;

    @Data
    public static class ApiUrl {

        //获取accessTokenUrl
        private String accessTokenUrl;
        //获取模版消息列表
        private String allPrivateTemplateUrl;
        //发送模版消息
        private String sendTemplateMsgUrl;
        //获取用户信息所需code
        private String authCodeUrl;
        //获取用户信息所需accessToken
        private String authAccessTokenUrl;
        //获取用户信息
        private String userInfoUrl;
        //批量获取用户信息
        private String batchUserInfoUrl;
        //批量获取用户openid列表
        private String openIdListUrl;
        //创建普通菜单
        private String menuCreateUrl;
        //创建个性化菜单
        private String menuCreateConditionalUrl;
        //获取菜单
        private String menuGetUrl;
        //删除菜单
        private String menuDeleteUrl;
        //更新用户分组
        private String groupMemberUpdate;
        //创建带参二维码
        private String createQrCode;
        //展示带参二维码
        private String showQrCode;
        //获取jsapi_ticket
        private String jsApiTicketUrl;
        //高级图文群发接口
        private String messageMassSendUrl;

        //汽车卡接口分割
        //汽车卡凭证
        private String cardApiTicketUrl;
        //汽车卡创建
        private String carCardCreateUrl;
        //汽车卡更新
        private String carCardUpdateUrl;
        //汽车卡code解码
        private String carCardCodeDecryptUrl;
        //汽车卡激活
        private String carCardActiveUrl;
        //个人领取汽车卡字段更新
        private String cardUpdateUserUrl;
        //修改库存接口
        private String modifyStockUrl;
    }
}
