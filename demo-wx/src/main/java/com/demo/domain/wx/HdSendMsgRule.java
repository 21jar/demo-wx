package com.demo.domain.wx;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HdSendMsgRule implements Serializable {

    private static final long serialVersionUID = 4632379717706509249L;

    private Integer id;

    private String templateId;

    private Integer type;

    private Integer remindTime;

    private Integer times;

    private String content;

    private String url;

    private String remark;

    private Date createTime;

    private Date updateTime;
}