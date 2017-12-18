package com.demo.dto.za;

import lombok.Data;

import java.util.List;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QuotePriceInput extends BaseInput {
    //投保流程编码
    private String insureFlowCode;
    //商业险起期
    private String businessEffectiveDate;
    //商业险止期
    private String businessExpireDate;
    //交强险起期
    private String compelEffectiveDate;
    //交强险止期
    private String compelExpireDate;
    //是否投保交强险
    private String isInsureCompelInsurance;
    //足额投保标志
    private String fullCoverageFlag;
    //险别信息列表
    private List<Coverage> coverageList;
}
