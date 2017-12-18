package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QueryVehicleModelInput extends BaseInput {

    //关键字查询
    private String vehicleBrandModelKey;

    //品牌编码
    private String brandCode;

    //车系
    private String familyCode;

    //档位
    private String gearboxType;

    //排量
    private String engineDesc;

    //页码
    private String pageNo;

    //每页显示数量
    private String pageSize;
}
