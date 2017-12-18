package com.demo.dto.za;

import lombok.Data;

import java.util.List;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class ApplyInquiryPriceOutput extends BaseOutput {

    private String insureFlowCode;
    private String vehicleBrand;
    private String vehicleFamily;
    private String vehicleDisplacement;
    private String vehicleGear;
    private String vehicleConfigurationMode;
    private String vehicleAcquisitionPrice;
    private String vehicleModel;
    private String vehicleImportMark;
    private String vehiclePassengerCap;
    private String vehicleModelCode;
    private String businessEffectiveDate;
    private String compelEffectiveDate;
    private String serialVersionUID;
    private List<CoverageInfoList> coverageInfoList;

    @Data
    public static class CoverageInfoList {
        private String baseRiderType;
        private String parentCoverageCode;
        private String coverageCode;
        private String coverageName;
        private String isNonDeductible;
        private List<String> sumInsureds;
        private String glassTypes;
    }
}
