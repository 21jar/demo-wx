package com.demo.domain.wx;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import com.demo.core.constant.CommonConstant;
@Getter
@Setter
@Data
public class HdFans implements Serializable {
    private static final long serialVersionUID = 3840949905508655995L;

    public static final String STATE_SUBSCRIBE = CommonConstant.SUBSCRIBE_STATUS;
    public static final String STATE_UNSUBSCRIBE = CommonConstant.UN_SUBSCRIBE_STATUS;
    
    private String openId;

    private String sex;

    private String nickName;

    private String province;

    private String city;

    private String country;

    private String headUrl;

    private String state;

    private Date subscribeTime;

    private Date unsubscribeTime;

    private String actionInfo;

    private Date createTime;

    private Date updateTime;
}