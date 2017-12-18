package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */

@Data
public class ApplyInquiryPriceInput extends BaseInput {

    // 投保城市代码
    private String insurePlaceCode;

    // 车牌号
    private String vehicleLicencePlateNo;

    // 车架号
    private String vehicleFrameNo;

    // 发动机号
    private String vehicleEngineNo;

    // 车辆登记注册日期
    private String vehicleRegisterDate;

    // 车主名称
    private String vehicleOwnerName;

    // 车主身份证
    private String vehicleOwnerCertificateNo;

    // 车主手机号
    private String vehiclePhoneNo;
    // 是否过户
    private int isTransferCar;
    // 过户日期
    private String transferDate;
    // 众安车型编码
    private String vehicleModelCode;

    // 贷款车标志
    private Integer loanCarFlag;

    // 所属产品
    private Integer productSource;

}
