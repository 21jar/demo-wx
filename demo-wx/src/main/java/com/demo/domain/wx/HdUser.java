package com.demo.domain.wx;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HdUser implements Serializable{

    private static final long serialVersionUID = -6910078669513010671L;

    private String openId;

    private String name;

    private String mobile;

     

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}