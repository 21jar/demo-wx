package com.demo.core.weixin.msg;

import lombok.Data;

/**
 * @author hst
 */
@Data
public abstract class BaseReplyMsg {
    // 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发人员微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/image/news/music/voice/video
    private String MsgType;

    protected String getMsgType() {
        return MsgType;
    }

    protected void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append("<ToUserName><![CDATA[");
        msg.append(this.getToUserName());
        msg.append("]]></ToUserName>");

        msg.append("<FromUserName><![CDATA[");
        msg.append(this.getFromUserName());
        msg.append("]]></FromUserName>");

        msg.append("<CreateTime>");
        msg.append(System.currentTimeMillis() / 1000);
        msg.append("</CreateTime>");

        msg.append("<MsgType><![CDATA[");
        msg.append(this.getMsgType());
        msg.append("]]></MsgType>");
        return msg.toString();
    }

    public abstract String toXml();
}
