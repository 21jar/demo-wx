package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 微信用户信息
 *
 * @author hst on 2016/12/13
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenUser extends BaseResult implements Serializable {
    private static final long serialVersionUID = -315690901169457172L;
    private Integer subscribe;

    @JsonProperty("openid")
    @JSONField(name = "openid")
    private String openId;

    @JsonProperty("nickname")
    @JSONField(name = "nickname")
    private String nickName;

    private String sex;

    private String city;

    private String country;

    private String province;

    private String language;

    @JsonProperty("headimgurl")
    @JSONField(name = "headimgurl")
    private String headImgUrl;

    @JsonProperty("subscribe_time")
    @JSONField(name = "subscribe_time")
    private long subscribeTime;

    @JsonProperty("unionid")
    @JSONField(name = "unionid")
    private String unionId;

    private String remark;

    @JsonProperty("groupid")
    @JSONField(name = "groupid")
    private String groupId;
}
