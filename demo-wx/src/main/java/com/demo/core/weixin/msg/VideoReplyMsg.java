package com.demo.core.weixin.msg;

import com.demo.core.weixin.constant.WxMsgType;

import lombok.Getter;
import lombok.Setter;

/**
 * 自动回复图片消息
 *
 * @author hst on 2016/12/13
 */
@Getter
@Setter
public class VideoReplyMsg extends BaseReplyMsg {
    private String mediaId;
    private String title;
    private String description;

    public VideoReplyMsg() {
        super.setMsgType(WxMsgType.VIDEO.getName());
    }

    public VideoReplyMsg(String fromUser, String toUser, String mediaId, String title, String description) {
        super.setMsgType(WxMsgType.VIDEO.getName());
        super.setFromUserName(fromUser);
        super.setToUserName(toUser);
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toXml() {
        StringBuilder msg = new StringBuilder();
        msg.append("<xml>");
        msg.append(super.toString());
        msg.append("<Video><MediaId><![CDATA[");
        msg.append(this.getMediaId());
        msg.append("]]></MediaId>");
        msg.append("<Title><![CDATA[");
        msg.append(this.getTitle());
        msg.append("]]></Title>");
        msg.append("<Description><![CDATA[");
        msg.append(this.getDescription());
        msg.append("]]></Description><Video>");
        msg.append("</xml>");
        return msg.toString();
    }
}
