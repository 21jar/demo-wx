package com.demo.domain.wx;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HdQrCodeScanRecord implements Serializable {

    private static final long serialVersionUID = 5094470739230778717L;

    private Integer id;

    private String openId;

    private Integer type;

    private String sceneStr;

    private Integer sceneId;

    private String scene;

    private Date createTime;

    private Date updateTime;
}