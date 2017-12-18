package com.demo.core.exception;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: 胡超云
 * @Version: 1.0.0
 * @Createdate: 2016-04-10 20:00
 * @Updatedate: -
 * @Description: -
 */
public class BusinessException extends BaseException {

    /**
     * Constructor
     *
     * @param errorCode
     */
    public BusinessException(String errorCode) {
        super(null, null, errorCode, null, null, null);
    }

    public BusinessException(String errorCode, JSONObject data) {
        super(null, null, errorCode, null, data, null);
    }

    /**
     * Constructor
     *
     * @param errorCode
     * @param parameters
     */
    public BusinessException(String errorCode, Object[] parameters) {
        super(null, null, errorCode, parameters, null, null);
    }

    /**
     * Constructor
     *
     * @param errorCode
     * @param e
     */
    public BusinessException(String errorCode, Throwable e) {
        super(null, null, errorCode, null, null, e);
    }

    /**
     * Constructor
     *
     * @param errorCode
     * @param parameters
     * @param e
     */
    public BusinessException(String errorCode, Object[] parameters, Throwable e) {
        super(null, null, errorCode, parameters, null, e);
    }

    /**
     * Constructor
     *
     * @param errorMsg
     * @param errorCode
     */
    public BusinessException(String errorMsg, String errorCode) {
        super(errorMsg, null, errorCode, null, null, null);
    }

    /**
     * Constructor
     *
     * @param errorMsg
     * @param errorCode
     * @param parameters
     */
    public BusinessException(String errorMsg, String errorCode,
                             Object[] parameters) {
        super(errorMsg, null, errorCode, parameters, null, null);
    }

    /**
     * Constructor
     *
     * @param errorMsg
     * @param errorCode
     * @param parameters
     * @param e
     */
    public BusinessException(String errorMsg, String errorCode,
                             Object[] parameters, Throwable e) {
        super(errorMsg, null, errorCode, parameters, null, e);
    }

    /**
     * Constructor
     *
     * @param errorMsg
     * @param errorDetail
     * @param errorCode
     */
    public BusinessException(String errorMsg, String errorDetail,
                             String errorCode) {
        super(errorMsg, errorDetail, errorCode, null, null, null);
    }

    /**
     * Constructor
     *
     * @param errorMsg
     * @param errorDetail
     * @param errorCode
     * @param e
     */
    public BusinessException(String errorMsg, String errorDetail,
                             String errorCode, Throwable e) {
        super(errorMsg, errorDetail, errorCode, null, null, e);
    }

    /**
     * Constructor
     *
     * @param errorMsg
     * @param errorDetail
     * @param errorCode
     * @param parameters
     */
    public BusinessException(String errorMsg, String errorDetail,
                             String errorCode, Object[] parameters) {
        super(errorMsg, errorDetail, errorCode, parameters, null, null);
    }

    /**
     * Constructor
     *
     * @param errorMsg
     * @param errorDetail
     * @param errorCode
     * @param parameters
     * @param e
     */
    public BusinessException(String errorMsg, String errorDetail,
                             String errorCode, Object[] parameters, Throwable e) {
        super(errorMsg, errorDetail, errorCode, parameters, null, e);
    }


}
