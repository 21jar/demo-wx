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
public class ImageReplyMsg extends BaseReplyMsg {
    private String mediaId;

    public ImageReplyMsg() {
        super.setMsgType(WxMsgType.IMAGE.getName());
    }

    public ImageReplyMsg(String fromUser, String toUser, String mediaId) {
        super.setMsgType(WxMsgType.IMAGE.getName());
        super.setFromUserName(fromUser);
        super.setToUserName(toUser);
        this.mediaId = mediaId;
    }

    @Override
    public String toXml() {
        StringBuilder msg = new StringBuilder();
        msg.append("<xml>");
        msg.append(super.toString());
        msg.append("<Image><MediaId><![CDATA[");
        msg.append(this.getMediaId());
        msg.append("]]></MediaId><Image>");
        msg.append("</xml>");
        return msg.toString();
    }
}
