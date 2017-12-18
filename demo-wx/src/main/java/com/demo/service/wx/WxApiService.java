package com.demo.service.wx;

import com.demo.core.weixin.pojo.WxMsgValidParams;
import com.demo.core.weixin.wxobj.AccessToken;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.demo.core.weixin.wxobj.result.QrCodeResult;
import com.google.zxing.WriterException;

import java.io.IOException;

/**
 * WxApi一些简单方法包装
 *
 * @author hst on 2016/12/15
 */
public interface WxApiService {

    AccessToken accessToken();

    /*
    * 验证微信消息正确性
    * */
    boolean signature(String signature, String timestamp, String nonce);

    /*
    * 验证微信消息正确性
    * */
    boolean signature(WxMsgValidParams validParams);

    /**
     * 增加人员到门店管理员组
     *
     * @param openId 用户id
     */
    BaseResult addUserToStoreAdminGroup(String openId);

    /**
     * 增加人员到门店管理员组
     *
     * @param openId 用户id
     */
    BaseResult removeUserToStoreAdminGroup(String openId);

    
}
