package com.demo.core.weixin.msg;

import com.demo.core.weixin.constant.WxMsgType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hst
 */
@Getter
@Setter
public class NewsForJsonArticlesReplyMsg extends BaseReplyMsg {
    private int ArticleCount = 0;
    private String Articles;

    public NewsForJsonArticlesReplyMsg() {
        super.setMsgType(WxMsgType.NEWS.getName());
    }

    public NewsForJsonArticlesReplyMsg(String fromUser, String toUser, int articleCount, String articles) {
        super.setMsgType(WxMsgType.NEWS.getName());
        super.setFromUserName(fromUser);
        super.setToUserName(toUser);
        ArticleCount = articleCount;
        Articles = articles;
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("<xml>");
        msg.append(super.toString());
        msg.append("<ArticleCount>").append(String.valueOf(this.getArticleCount())).append("</ArticleCount>");
        msg.append(Articles);
        msg.append("</xml>");
        return msg.toString();
    }

    @Override
    public String toXml() {
        return this.toString();
    }
}
