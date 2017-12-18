package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.io.Serializable;

/**
 * accessToken对象
 *
 * @author hst on 2016/11/29
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class AccessToken extends BaseResult implements Serializable {

    //accessToken最多生存100分钟必须刷新，单位ms
    public static long MAX_LIVE_TIME = 6000000;
    //缓存时长100分钟，单位s
    public static Integer EXPIRE_TIME = 6000;

    @JsonProperty("access_token")
    @JSONField(name = "access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    @JSONField(name = "expires_in")
    private int expiresIn;
    //上次刷新时间
    private long lastRefreshTime;

    public AccessToken(String accessToken, int expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.lastRefreshTime = System.currentTimeMillis();
    }

    public AccessToken(String accessToken, int expiresIn, long lastRefreshTime) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.lastRefreshTime = lastRefreshTime;
    }
}
