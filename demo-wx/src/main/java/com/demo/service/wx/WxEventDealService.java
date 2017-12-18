package com.demo.service.wx;

/**
 * 模版消息发送结果回调处理
 * <p>
 * Created by hst on 2016/11/30.
 */
public interface WxEventDealService {

    /**
     * 更新模版消息发送结果
     *
     * @param openId wx用户id
     * @param msgId 微信发送该条模版信息时生成的id
     * @param status 发送结果
     */
    void updateTemplateSendResult(String openId, String msgId, String status);

    /**
     * 保存关注用户的信息
     *
     * @param openId wx用户id
     * @param actionInfo 带参二维码值
     * @param createTime 微信消息推送时间
     */
    void saveSubscribeUser(String openId, String actionInfo, Long createTime);

    /**
     * 更新取消关注用户的信息
     *
     * @param openId wx用户id
     */
    void updateUnSubscribeUser(String openId);

    /**
     * 更新用户的带参二维码值
     */
    void recordQrCodeScan(String openId, String actionInfo, Long createTime);

    /**
     * 取消用户管理员权限
     *
     * @param openId wx用户id
     */
    void cancelUserStoreManagerPrivilege(String openId);

    /*
    * 保存用户领取的卡券信息
    * */
    void saveUserCardInfo(String openId, String cardId, String code, String outerStr);

    /*
    * 更新汽车卡审核结果
    * */
    void updateCardCheckStatus(String checkStatus, String wxCardId);
}
