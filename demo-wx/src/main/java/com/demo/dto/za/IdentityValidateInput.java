package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class IdentityValidateInput extends BaseInput {

    private String insureFlowCode;
    private String vehicleOwnerName;
    private String vehicleOwnerCertificateNo;
    private String vehicleOwnerCertificateType;
    private String vehicleOwnerSex;
    private String vehicleOwnerBirthday;

    private String applicantName;
    private String applicantCertificateNo;
    private String applicantCertificateType;
    private String applicantSex;
    private String applicantBirthday;
    private String applicantEmail;
    private String applicantAge;
    private String applicantNationality;
    private String applicantProvinceCode;
    private String applicantCityCode;
    private String applicantDistrictCode;
    private String applicantAddress;
    private String applicantPostcode;
    private String applicantPhoneNo;

    private String distributionProvinceCode;
    private String distributionCityCode;
    private String distributionDistrictCode;
    private String distributionName;
    private String distributionAddress;
    private String distributionPhoneNo;

    private String insurantName;
    private String insurantCertificateType;
    private String insurantCertificateNo;
    private String insurantPhoneNo;
    private String insurantEmail;
    private String insurantSex;
    private String insurantBirthday;
    private String insurantAge;
    private String insurantNationality;
    private String insurantProvinceCode;
    private String insurantCityCode;
    private String insurantDistrictCode;
    private String insurantAddress;
    private String insurantPostcode;
}
