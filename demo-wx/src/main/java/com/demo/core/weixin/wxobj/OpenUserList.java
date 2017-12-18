package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author by hst on 2016/4/6.
 *         开发者可通过该接口来批量获取用户基本信息。最多支持一次拉取100条。
 */
@Getter
@Setter
public class OpenUserList extends BaseResult {

    @JsonProperty("user_info_list")
    @JSONField(name = "user_info_list")
    private List<OpenUser> userInfoList;
}
