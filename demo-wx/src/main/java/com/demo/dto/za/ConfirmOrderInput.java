package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */

@Data
public class ConfirmOrderInput  extends BaseInput {

    //投保流程编码
    private String insureFlowCode;
    //商户唯一订单号
    private String outTradeNo;
    //车主姓名
    private String vehicleOwnerName;
    //车主证件号码
    private String vehicleOwnerCertificateNo;
    //车主证件号码
    private String vehicleOwnerCertificateType;
    //车主性别
    private String vehicleOwnerSex;
    //车主生日
    private String vehicleOwnerBirthday;
    //投保人姓名
    private String applicantName;
    //投保人证件号码
    private String applicantCertificateNo;
    //投保人证件类型
    private String applicantCertificateType;
    //投保人性别
    private String applicantSex;
    //投保人生日
    private String applicantBirthday;
    //投保人邮件
    private String applicantEmail;
    //投保人年龄
    private String applicantAge;
    //投保人国籍
    private String applicantNationality;
    //投保人省份编码
    private String applicantProvinceCode;
    //投保人城市编码
    private String applicantCityCode;
    //投保人区县编码
    private String applicantDistrictCode;
    //投保人地址
    private String applicantAddress;
    //投保人邮编
    private String applicantPostcode;
    //投保人手机
    private String applicantPhoneNo;
    //收件人省份编码
    private String distributionProvinceCode;
    //收件人城市编码
    private String distributionCityCode;
    //收件人区县编码
    private String distributionDistrictCode;
    //收件人姓名
    private String distributionName;
    //收件详细地址
    private String distributionAddress;
    //收件人电话
    private String distributionPhoneNo;
    //被保人姓名
    private String insurantName;
    //被保人证件类型
    private String insurantCertificateType;
    //被保人证件号码
    private String insurantCertificateNo;
    //被保人手机
    private String insurantPhoneNo;
    //被保人邮件
    private String insurantEmail;
    //被保人性别
    private String insurantSex;
    //被保人生日
    private String insurantBirthday;
    //被保人年龄
    private String insurantAge;
    //被保人国籍
    private String insurantNationality;
    //被保人省份编码
    private String insurantProvinceCode;
    //被保人城市编码
    private String insurantCityCode;
    //被保人区县编码
    private String insurantDistrictCode;
    //被保人地址
    private String insurantAddress;
    //被保人邮编
    private String insurantPostcode;
    //验证码
    private String validateCode;

}
