package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 个性化菜单配置
 *
 * @author hst on 2016/12/13
 */
@Getter
@Setter
public class ConditionMenu extends Menu {
    @JSONField(name = "matchrule")
    @JsonProperty("matchrule")
    private MatchRule matchRule;

    public ConditionMenu(List<Button> buttons) {
        super(buttons);
    }

    public ConditionMenu(List<Button> buttons, MatchRule matchRule) {
        super(buttons);
        this.matchRule = matchRule;
    }

    @Getter
    @Setter
    public static class MatchRule {
        @JSONField(name = "group_id")
        @JsonProperty("group_id")
        private String groupId;

        private Integer sex;

        private String country;

        private String province;

        private String city;

        @JSONField(name = "client_platform_type")
        @JsonProperty("client_platform_type")
        private Integer clientPlatformType;

        public MatchRule(String groupId) {
            this.groupId = groupId;
        }
    }
}
