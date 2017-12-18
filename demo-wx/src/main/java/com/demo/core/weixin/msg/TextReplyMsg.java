package com.demo.core.weixin.msg;

import com.demo.core.weixin.constant.WxMsgType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextReplyMsg extends BaseReplyMsg {
    //消息内容
    private String content;

    public TextReplyMsg() {
        super.setMsgType(WxMsgType.TEXT.getName());
    }


    public TextReplyMsg(String fromUser, String toUser, String content) {
        super.setMsgType(WxMsgType.TEXT.getName());
        super.setFromUserName(fromUser);
        super.setToUserName(toUser);
        this.content = content;
    }

    public TextReplyMsg(String content) {
        super.setMsgType(WxMsgType.TEXT.getName());
        this.content = content;
    }

    @Override
    public String toXml() {
        StringBuilder msg = new StringBuilder();
        msg.append("<xml>");
        msg.append(super.toString());
        msg.append("<Content><![CDATA[");
        msg.append(this.getContent());
        msg.append("]]></Content>");
        msg.append("</xml>");
        return msg.toString();
    }
}
