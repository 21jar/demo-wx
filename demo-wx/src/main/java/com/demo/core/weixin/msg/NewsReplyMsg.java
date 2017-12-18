package com.demo.core.weixin.msg;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.demo.core.weixin.constant.WxMsgType;

/**
 * @author hst
 */
@Getter
@Setter
public class NewsReplyMsg extends BaseReplyMsg {
    private int ArticleCount = 0;
    private Articles Articles;

    public NewsReplyMsg() {
        super.setMsgType(WxMsgType.NEWS.getName());
    }

    public NewsReplyMsg(String fromUser, String toUser, int articleCount, NewsReplyMsg.Articles articles) {
        super.setMsgType(WxMsgType.NEWS.getName());
        super.setFromUserName(fromUser);
        super.setToUserName(toUser);
        ArticleCount = articleCount;
        Articles = articles;
    }

    @Getter
    @Setter
    public static class Articles {
        private List<Item> item;

        public Articles() {
        }

        public Articles(List<Item> item) {
            this.item = item;
        }

        public String toString() {
            StringBuilder msg = new StringBuilder();
            msg.append("<Articles>");
            this.getItem().forEach(item1 -> {
                msg.append(item);
            });
            msg.append("</Articles>");
            return msg.toString();
        }
    }

    @Getter
    @Setter
    public static class Item {
        private String Title;
        private String Description;
        private String PicUrl;
        private String Url;

        public Item() {
        }

        public Item(String title, String description, String picUrl, String url) {
            Title = title;
            Description = description;
            PicUrl = picUrl;
            Url = url;
        }

        public String toString() {
            StringBuilder msg = new StringBuilder();
            msg.append("<item>");
            msg.append("<Title><![CDATA[").append(this.getTitle()).append("]]></Title>");
            msg.append("<Description><![CDATA[").append(this.getDescription()).append("]]></Description>");
            msg.append("<PicUrl><![CDATA[").append(this.getPicUrl()).append("]]></PicUrl>");
            msg.append("<Url><![CDATA[").append(this.getUrl()).append("]]></Url>");
            msg.append("</item>");
            return msg.toString();
        }
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("<xml>");
        msg.append(super.toString());
        msg.append("<ArticleCount>").append(String.valueOf(this.getArticleCount())).append("</ArticleCount>");
        msg.append(Articles.toString());
        msg.append("</xml>");
        return msg.toString();
    }

    @Override
    public String toXml() {
        return this.toString();
    }
}
