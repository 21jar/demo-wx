package com.demo.dto.za;

import lombok.Data;

import java.util.List;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QueryPolicyInfoOutput extends BaseOutput {
    private PolicyInfo policyInfo;
    private VehicleInfo vehicleInfo;
    private CustomerInfo customerInfo;
    private List<CoverageList> coverageList;

    @Data
    public static class VehicleInfo {
        private String vehicleLicencePlateNo;
        private String vehicleBrand;
        private String vehicleFrameNo;
        private String vehicleEngineNo;
        private String vehicleRegisterDate;
        private String approvedCarrier;
        private String approvedLoad;
        private String vehicleType;
        private String vehicleUsageType;
    }

    @Data
    public static class CustomerInfo {
        private String vehicleOwnerName;
        private String applicantName;
        private String insurantName;
    }

    @Data
    public static class PolicyInfo {
        private String policyNo;
        private String policyStatus;
        private String applyNo;
        private String taxPreimum;
        private String taxType;
        private String taxPayableThisYear;
        private String taxPayableLastYear;
        private String taxLateCharge;
    }

    @Data
    public static class CoverageList {
        private String baseRiderType;
        private String parentCoverageCode;
        private String coverageCode;
        private String coverageName;
        private String isNonDeductible;
        private String sumInsured;
        private String coveragePreimum;
        private String glassType;
    }

}
