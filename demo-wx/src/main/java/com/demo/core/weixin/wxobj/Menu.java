package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.core.weixin.constant.KeyButtonType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Menu {
    private List<Button> button;
    @JSONField(name = "menuid")
    @JsonProperty("menuid")
    private String menuId;

    public Menu(List<Button> button) {
        this.button = button;
    }

    @Getter
    @Setter
    public static class Button {
        private String name;
        private String type;
        private Integer order;
    }

    @Getter
    @Setter
    public static class ParentButton extends Button {
        private String key;
        @JSONField(name = "sub_button")
        @JsonProperty("sub_button")
        private List<Button> subButton = new ArrayList<>();

        public ParentButton(String name, String key, Integer order) {
            super.setName(name);
            super.setOrder(order);
            this.key = key;
        }
    }

    @Getter
    @Setter
    public static class ViewButton extends Button {
        private String url;

        public ViewButton(String name, String url, Integer order) {
            super.setType("view");
            super.setName(name);
            super.setOrder(order);
            this.url = url;
        }
    }

    @Getter
    @Setter
    public static class KeyButton extends Button {
        private String key;

        public KeyButton(KeyButtonType type, String name, String key, Integer order) {
            super.setType(type.getName());
            super.setName(name);
            super.setOrder(order);
            this.key = key;
        }
    }
}
