package com.demo.dto.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传结果返回
 *
 * @author hst on 2016/12/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResult {
    public static final String ERR_CODE = "-1";
    public static final String ERR_MSG = "服务器错误";
    public static final String EMPTY_MSG = "文件为空或长度为0！";
    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MSG = "ok";

    @JsonProperty("errcode")
    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    @JsonProperty("errmsg")
    private String errMsg;

    private String url;

    public static UploadResult emptyResult() {
        return new UploadResult(ERR_CODE, EMPTY_MSG, "");
    }

    public static UploadResult errorResult(String msg) {
        return new UploadResult(ERR_CODE, msg, "");
    }

    public static UploadResult successResult(String url) {
        return new UploadResult(SUCCESS_CODE, SUCCESS_MSG, url);
    }
}
