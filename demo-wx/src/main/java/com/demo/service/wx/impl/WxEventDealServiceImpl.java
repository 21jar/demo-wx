package com.demo.service.wx.impl;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.annotation.MyCacheEvict;
import com.demo.core.annotation.TargetDatabase;
import com.demo.core.constant.CommonConstant;
import com.demo.core.weixin.WxApi;
import com.demo.core.weixin.constant.WxLimitQrCodeType;
import com.demo.core.weixin.constant.WxMsgStatus;
import com.demo.core.weixin.tool.ResultCheck;
import com.demo.core.weixin.wxobj.OpenUser;
import com.demo.domain.wx.HdFans;
import com.demo.domain.wx.HdQrCodeScanRecord;
import com.demo.mapper.wx.HdFansMapper;
import com.demo.mapper.wx.HdQrCodeScanRecordMapper;
import com.demo.service.wx.WxEventDealService;

/**
 * 模版消息发送结束回调处理
 *
 * @author hst on 2016/11/30
 */
@Slf4j
@Service
public class WxEventDealServiceImpl implements WxEventDealService {

    private ExecutorService activatePool = Executors.newFixedThreadPool(60);

    
    @Autowired
    private HdQrCodeScanRecordMapper hdQrCodeScanRecordMapper;
//    @Autowired
//    private CarCardService carCardService;
//    @Autowired
//    private HdStoreAdminMapper hdStoreAdminMapper;
    @Autowired
    private HdFansMapper hdFansMapper;
    @Autowired
    private WxApi wxApi;

    @Override
    @TargetDatabase
    public void updateTemplateSendResult(String openId, String msgId, String status) {
        boolean isSuccess = WxMsgStatus.isSuccessMsg(status);
//        HdSendMsgRecord msgRecord = new HdSendMsgRecord();
//        msgRecord.setMsgId(msgId);
//        msgRecord.setIsSuccess(isSuccess ? 1 : 0);
//        msgRecord.setSendResult(status);
        //hdSendMsgRecordMapper.updateResultStatusByMsgId(msgRecord);

        if (!isSuccess) {
            log.error("MsgId: {}, openId: {}, 模版信息发送失败! status: {}", msgId, openId, status);
        }
    }

    @Override
    @MyCacheEvict(key = "t7.#openId")
    @TargetDatabase
    public void saveSubscribeUser(String openId, String actionInfo, Long createTime) {
        OpenUser openUser = wxApi.getUserInfo(openId);
        if (ResultCheck.isSuccess(openUser)) {
            saveQrCodeScan(openId, actionInfo, createTime);
            try {
                HdFans fans = new HdFans();
                BeanUtils.copyProperties(openUser, fans);
                fans.setHeadUrl(openUser.getHeadImgUrl());
                fans.setState(String.valueOf(openUser.getSubscribe()));
                //二维码参数
                fans.setActionInfo(actionInfo);
                fans.setSubscribeTime(new Date(openUser.getSubscribeTime() * 1000));
                fans.setCreateTime(new Date(createTime));

                hdFansMapper.insert(fans);
                //log.info("保存关注用户信息：{}, 二维码参数为：{}", openUser.getOpenId(), actionInfo);
            } catch (Exception e) {
                // log.error("保存关注用户信息失败：{}", e.getMessage());
                hdFansMapper.saveErrorOpenId(openId);
                //log.info("用户信息保存失败({})，已插入hd_fans_error_record。", openId);
            }
        } else {
            //log.error("获取用户信息失败：openId = {}, 错误信息 = {}", openId, openUser);
        }
    }

    @Override
    @TargetDatabase
    @MyCacheEvict(key = "t7.#openId")
    public void updateUnSubscribeUser(String openId) {
        HdFans fans = new HdFans();
        fans.setOpenId(openId);
        fans.setState(CommonConstant.UN_SUBSCRIBE_STATUS);
        fans.setUnsubscribeTime(new Date());
        hdFansMapper.updateUnSubscribeUser(fans);
        log.info("更新取消关注用户信息，openId = {}", openId);
    }

    @Override
    @Transactional
    @TargetDatabase
    public void recordQrCodeScan(String openId, String actionInfo, Long createTime) {
        hdFansMapper.updateUserActionInfo(openId, actionInfo);
        saveQrCodeScan(openId, actionInfo, createTime);
    }

    @Override
    @TargetDatabase
    @Transactional
    public void cancelUserStoreManagerPrivilege(String openId) {
//        hdStoreAdminMapper.cancelManagerPrivilege(openId, "用户取消关注");
//        hdStoreAdminMapper.updateManagerApplyState(openId, "用户取消关注");
    }

    @Override
    public void saveUserCardInfo(String openId, String cardId, String code, String outerStr) {
//        HdCarCode carCode = new HdCarCode();
//        carCode.setCode(code);
//        carCode.setCardId(cardId);
//        carCode.setOpenId(openId);
//        carCode.setVinNum(outerStr);
//        carCode.setOuterStr(outerStr);

//        activatePool.execute(() -> {
//            int result = carCardService.receiveCard(carCode);
//            if (result == 0) {
//                log.info("汽车卡保存失败：{}", carCode);
//            } else if (result == 1) {
//                log.info("汽车卡保存成功：{}", carCode);
//            } else {
//                log.info("汽车卡保存成功,且激活成功：{}", carCode);
//            }
//        });
    }

    @Override
    public void updateCardCheckStatus(String checkStatus, String wxCardId) {
        //log.info("更新汽车卡审核状态结果：{}", carCardService.updateCardCheckStatus(checkStatus, wxCardId));
    }

    private void saveQrCodeScan(String openId, String actionInfo, Long createTime) {
        if (!StringUtils.isEmpty(actionInfo)) {
            HdQrCodeScanRecord qrCodeScanRecord = new HdQrCodeScanRecord();
            qrCodeScanRecord.setOpenId(openId);
            qrCodeScanRecord.setSceneStr(actionInfo);
            //自定义永久二维码规则处理
            if (actionInfo.contains("_")) {
                String[] typeAndScene = actionInfo.split(WxLimitQrCodeType.SPLIT);
                qrCodeScanRecord.setType(Integer.valueOf(typeAndScene[0]));
                qrCodeScanRecord.setScene(typeAndScene[1]);
                //    数字
            } else if (StringUtils.isNumeric(actionInfo)) {
                qrCodeScanRecord.setSceneId(Integer.valueOf(actionInfo));
                //    字符串
            } else {
                qrCodeScanRecord.setScene(actionInfo);
                qrCodeScanRecord.setSceneStr(actionInfo);
            }
            qrCodeScanRecord.setCreateTime(new Date(createTime));

            hdQrCodeScanRecordMapper.insert(qrCodeScanRecord);
        }
    }
}
