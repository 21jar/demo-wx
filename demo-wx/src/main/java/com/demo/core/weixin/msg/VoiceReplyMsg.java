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
public class VoiceReplyMsg extends BaseReplyMsg {
    private String mediaId;

    public VoiceReplyMsg() {
        super.setMsgType(WxMsgType.VOICE.getName());
    }

    public VoiceReplyMsg(String fromUser, String toUser, String mediaId) {
        super.setMsgType(WxMsgType.VOICE.getName());
        super.setFromUserName(fromUser);
        super.setToUserName(toUser);
        this.mediaId = mediaId;
    }

    @Override
    public String toXml() {
        StringBuilder msg = new StringBuilder();
        msg.append("<xml>");
        msg.append(super.toString());
        msg.append("<Voice><MediaId><![CDATA[");
        msg.append(this.getMediaId());
        msg.append("]]></MediaId></Voice>");
        msg.append("</xml>");
        return msg.toString();
    }
}
