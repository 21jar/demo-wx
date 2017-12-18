package com.demo.core.weixin.msg;

import com.demo.core.weixin.constant.WxMsgType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferCustomerServiceMsg extends BaseReplyMsg {
    

    public TransferCustomerServiceMsg() {
        super.setMsgType(WxMsgType.TRANSFERCUSTOMERSERVICE.getName());
    }


    public TransferCustomerServiceMsg(String fromUser, String toUser) {
        super.setMsgType(WxMsgType.TRANSFERCUSTOMERSERVICE.getName());
        super.setFromUserName(fromUser);
        super.setToUserName(toUser);
    }


    @Override
    public String toXml() {
        StringBuilder msg = new StringBuilder();
        msg.append("<xml>");
        msg.append(super.toString());
        msg.append("</xml>");
        return msg.toString();
    }
}
