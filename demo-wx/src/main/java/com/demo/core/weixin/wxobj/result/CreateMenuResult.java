package com.demo.core.weixin.wxobj.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 创建菜单结果
 *
 * @author hst on 2016/12/13
 */
@Getter
@Setter
@ToString(callSuper = true)
public class CreateMenuResult extends BaseResult {

    @JSONField(name = "menuid")
    @JsonProperty("menuid")
    private String menuId;
}
