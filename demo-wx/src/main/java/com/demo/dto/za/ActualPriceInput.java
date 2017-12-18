package com.demo.dto.za;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualPriceInput extends BaseInput{
    //投保流程编码
    private String insureFlowCode;
    //商业险起期
    private String businessEffectiveDate;
    //车辆登记注册日期
    private String vehicleRegisterDate;
    //新车购置价
    private String purchasePrice;
}
