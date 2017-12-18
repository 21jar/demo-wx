package com.demo.dto.za;

import lombok.Data;

import java.util.List;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QueryCLInfoDto extends BaseOutput{

    private String vehicleLicencePlateNo;

    private String vehicleBrand;

    private String vehicleModel;

    private String vehicleFrameNo;

    private String vehicleEngineNo;

    private String vehicleRegisterDate;

    private List<VehicleModelList> vehicleModelList;

    @Data
    public static class VehicleModelList{

        private String vehicleFamily;

        private String vehicleDisplacement;

        private String vehicleGear;

        private String vehicleConfigurationMode;

        private String vehicleAcquisitionPrice;

        private String vehicleModel;

        private String vehiclePassengerCap;

        private String vehicleModelCode;

        private String vehicleBrand;
    }
}
