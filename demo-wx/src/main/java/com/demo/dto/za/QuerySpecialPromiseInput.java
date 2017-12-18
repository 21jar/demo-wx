package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QuerySpecialPromiseInput extends BaseInput{

    //投保流程ID
    private String insureFlowCode;
    //是否投商业险
    private String isInsureBussinessInsurance;
    //是否投交强险
    private String isInsureCompelInsurance;


    public QuerySpecialPromiseInput() {

    }

    public QuerySpecialPromiseInput(String insureFlowCode, String isInsureBussinessInsurance, String isInsureCompelInsurance) {
        this.insureFlowCode = insureFlowCode;
        this.isInsureBussinessInsurance = isInsureBussinessInsurance;
        this.isInsureCompelInsurance = isInsureCompelInsurance;
    }

}
