package com.demo.core.weixin.wxobj;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author  by hst on 2016/4/6.
 */
public class BatchUserInfoObj {
    /*
    * 开发者可通过该接口来批量获取用户基本信息。最多支持一次拉取100条。
    * */
    public static int MAX_NUM = 100;

    @JsonProperty("user_list")
    private List<BatchObj> userList;

    public static class BatchObj {
        @JsonProperty("openid")
        private String openId;

        private String lang;

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }
    }

    public BatchUserInfoObj() {
    }

    public BatchUserInfoObj(List<BatchObj> userList) {
        this.userList = userList;
    }

    public List<BatchObj> getUserList() {
        return userList;
    }

    public void setUserList(List<BatchObj> userList) {
        this.userList = userList;
    }
}
