package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class VehicleModelConfirmInput extends BaseInput {
    private String flowid;
    private String checkNo;
    private String checkCode;
}

