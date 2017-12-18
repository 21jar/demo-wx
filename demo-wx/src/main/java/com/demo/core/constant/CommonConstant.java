package com.demo.core.constant;

import java.io.Serializable;

/**
 * Created by 胡超云 on 2016/11/17.
 * 常量定义
 */

public class CommonConstant implements Serializable {

    private static final long serialVersionUID = 1909930235395369869L;

    //纳智捷命名空间简称
    //公用缓存全部都用这个分区
    public final static String NAMESPACE_DB = "db";

    //维修预约状态
    public static final Integer MAINTAIN_MEET_SUCCESS = 1;
    public static final Integer MAINTAIN_MEET_CANCEL = 0;

    //Cookies中openId的命名
    public static final String OPEN_ID = "open_id";
    //cookie30天后过期
    public static final int OPEN_ID_LIVE_TIME = 60*60*24*30;

    public static final String SUBSCRIBE_STATUS = "1";
    public static final String UN_SUBSCRIBE_STATUS = "0";
}
