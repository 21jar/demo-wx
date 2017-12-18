package com.demo.core.weixin.wxobj;

import com.demo.core.weixin.wxobj.result.BaseResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by hst on 2016/4/6.
 */
@Getter
@Setter
public class OpenIdList extends BaseResult {

    public static int MAX_RECODE = 10000;
    /*
    * 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID
    * （加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
    * 一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求。
    * */

    //关注该公众账号的总用户数
    private int total;

    //拉取的OPENID个数，最大值为10000
    private int count;

    private Map<String, List<String>> data;

    @JsonIgnore
    //获得的openid劣列表
    private List<String> openId;

    @JsonProperty("next_openid")
    //拉取列表的最后一个用户的OPENID
    private String nextOpenid;
}
