package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class BaseOutput {

    private String result;

    private String resultMessage;

    private String requestNo;

    public boolean isSuccess() {
        return "0".equals(result);
    }
}
