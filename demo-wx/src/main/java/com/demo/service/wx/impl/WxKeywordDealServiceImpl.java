package com.demo.service.wx.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.core.weixin.constant.WxMsgType;
import com.demo.core.weixin.msg.*;
import com.demo.domain.wx.HdAutoReplyRule;
import com.demo.env.AutoReplyRule;
import com.demo.service.wx.WxKeywordDealService;

import java.util.Objects;

/**
 * 微信关键词自动回复
 *
 * @author hst on 2016/12/13
 */
@Slf4j
@Service
public class WxKeywordDealServiceImpl implements WxKeywordDealService {
    @Autowired
    private AutoReplyRule autoReplyRule;

    @Override
    public String genReplyByKeyWord(String fromUser, String toUser, String content) {
    	log.info(content);
        //log.info("收到来自 {} 用户的消息 {}", fromUser, content);
        String replyStr = "success";
        HdAutoReplyRule rule = autoReplyRule.getKeyWordRule(content);
        if (Objects.nonNull(rule)) {
            WxMsgType replyType = WxMsgType.getByName(rule.getReplyType());
            switch (replyType) {
                case TEXT:
                    replyStr = new TextReplyMsg(toUser, fromUser, rule.getContent()).toXml();
                    break;
                case IMAGE:
                    replyStr = new ImageReplyMsg(toUser, fromUser, rule.getContent()).toXml();
                    break;
                case VOICE:
                    replyStr = new VoiceReplyMsg(toUser, fromUser, rule.getContent()).toXml();
                    break;
                case VIDEO:
                    replyStr = new VideoReplyMsg(toUser, fromUser, rule.getContent(), rule.getTitle(), rule.getDescription()).toXml();
                    break;
                case NEWS:
                    replyStr = new NewsForJsonArticlesReplyMsg(toUser, fromUser, rule.getArticleCount(), rule.getArticles()).toXml();
                    break;
                default:
                    break;
            }
            //log.info("成功回复消息 {} ", replyStr);
        }else{
        	replyStr = new TransferCustomerServiceMsg(toUser, fromUser).toXml();
        }
        return replyStr;
    }
}
