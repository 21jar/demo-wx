package com.demo.core.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: 胡超云
 * @Version: 1.0.0
 * @Createdate: 2016-04-10 19:59
 * @Updatedate: -
 * @Description: -
 */
@Getter
@Setter
@ToString
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 245916272151692438L;

    protected String errorCode; // 错误代码(属性表文件中定义其message)
    protected String errorMsg; // 错误信息(For debug)
    protected Object[] parameters; // 参数列表(错误代码对应的message需要的参数列表)
    protected Throwable errorCause; // 错误发生原因(原始Exception)
    protected String errorDetail; // 错误详细信息
    protected JSONObject data;

    /**
     * Constructor
     *
     * @param errorMsg
     * @param errorCode
     * @param parameters
     * @param e
     */
    public BaseException(String errorMsg, String errorDetail, String errorCode,
                         Object[] parameters, JSONObject data, Throwable e) {
        super(e == null ? errorMsg : e.getMessage());

        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorDetail = errorDetail;
        this.errorCause = e;
        this.parameters = parameters;
        this.data = data;
    }
}
