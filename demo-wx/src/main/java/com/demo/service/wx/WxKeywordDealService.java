package com.demo.service.wx;

/**
 * ${DESCRIPTION}
 * <p>
 * Created by hst on 2016/12/13.
 */
public interface WxKeywordDealService {

    /**
     * 根据关键词回复相关消息
     *
     * @param fromUser 发送消息的用户id（openId）
     * @param toUser   公众号id（appId）
     * @param content  消息内容
     * @return 回复的消息
     */
    String genReplyByKeyWord(String fromUser, String toUser, String content);
}
