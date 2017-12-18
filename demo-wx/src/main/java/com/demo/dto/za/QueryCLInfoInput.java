package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QueryCLInfoInput extends BaseInput {

    //号牌种类
    private String vehicleLicenceType = "02";

    //号牌号码
    private String vehicleLicencePlateNo;

    //车主姓名
    private String vehicleOwnerName;

    //投保城市代码
    private String insurePlaceCode;
}
