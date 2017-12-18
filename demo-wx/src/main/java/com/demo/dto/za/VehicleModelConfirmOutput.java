package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class VehicleModelConfirmOutput extends BaseOutput {
    private String licenceNo;
    private String frameNo;
    private String engineNo;
    private String registerDate;
    private String vehicleOwner;
    private String vehicleType;
    private String vehicleStyle;
    private String vehicleModelCode;
}
