package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class VehicleModelCheckInput extends BaseInput {
    private String insurePlaceCode;
    private String vehicleLicencePlateNo;
    private String vehicleFrameNo;
}
