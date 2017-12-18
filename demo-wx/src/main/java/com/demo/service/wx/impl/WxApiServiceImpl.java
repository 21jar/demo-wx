package com.demo.service.wx.impl;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.core.weixin.WxApi;
import com.demo.core.weixin.pojo.WxMsgValidParams;
import com.demo.core.weixin.wxobj.AccessToken;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.demo.env.WxProperties;
import com.demo.service.wx.WxApiService;

/**
 * @author hst on 2016/12/15
 */
@Slf4j
@Service
public class WxApiServiceImpl implements WxApiService {

    @Autowired
    private WxApi wxApi;
    @Autowired
    private WxProperties wxProperties;
    

    @Override
    public AccessToken accessToken() {
        return wxApi.accessToken();
    }

    @Override
    public boolean signature(String signature, String timestamp, String nonce) {
        return wxApi.signature(signature, timestamp, nonce);
    }

    @Override
    public boolean signature(WxMsgValidParams validParams) {
        return wxApi.signature(validParams);
    }

    @Override
    public BaseResult addUserToStoreAdminGroup(String openId) {
        return wxApi.groupMemberUpdate(openId, wxProperties.getStoreMangerGroupId());
    }

    @Override
    public BaseResult removeUserToStoreAdminGroup(String openId) {
        return wxApi.groupMemberUpdate(openId, wxProperties.getDefaultGroupId());
    }

    

    private File checkCache(String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            log.info("缓存文件夹创建结果：{}", file.getParentFile().mkdirs());
        }
        return file;
    }
}
