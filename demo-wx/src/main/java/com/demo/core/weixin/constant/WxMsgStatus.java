package com.demo.core.weixin.constant;

/**
 * 微信消息状态
 *
 * @author hst on 2016/11/30
 */
public enum WxMsgStatus {
    //成功状态
    SUCCESS("success"),
    //发送状态为用户拒绝接收
    FAILED_USER_BLOCK("failed:user block"),
    //发送状态为发送失败（非用户拒绝）
    FAILED_SYSTEM_FAILED("failed: system failed");

    private String status;

    WxMsgStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static boolean isSuccessMsg(String statusStr) {
        for (WxMsgStatus status : WxMsgStatus.values()) {
            if (status.getStatus().equals(statusStr)) {
                return true;
            }
        }
        return false;
    }
}
