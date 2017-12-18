package com.demo.domain.wx;

import lombok.Data;

import java.io.Serializable;

@Data
public class HdMenuConfig implements Serializable {

    private static final long serialVersionUID = -741723814777322329L;

    private Integer id;

    private Integer localMenuId;

    private String wxMenuId;

    private String button;

    private Integer buttonLevel;

    private Integer hasSubButton;

    private String name;

    private String type;

    private String key;

    private String url;

    private String mediaId;

    private Integer order;

    private String matchRuleGroup;

    private Integer updateToWx;
}