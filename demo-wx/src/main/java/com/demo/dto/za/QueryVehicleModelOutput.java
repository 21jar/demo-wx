package com.demo.dto.za;

import lombok.Data;

import java.util.List;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QueryVehicleModelOutput extends BaseOutput {

    private String totalCount;

    private List<String> engineDescList;

    private List<BrandList> brandList;

    private List<FamilyList> familyList;

    private List<VehicleModelList> vehicleModelList;

    @Data
    public static class BrandList {
        private String brandCode;

        private String brandName;
    }

    @Data
    public static class FamilyList {
        private String familyCode;

        private String familyName;
    }

    @Data
    public static class VehicleModelList {

        private String vehicleFamily;

        private String vehicleDisplacement;

        private String vehicleGear;

        private String vehicleConfigurationMode;

        private String vehicleAcquisitionPrice;

        private String vehicleModel;

        private String vehiclePassengerCap;

        private String vehicleModelCode;

        private String vehicleBrand;

        private String passengerCapMin;

        private String passengerCapMax;
    }
}
