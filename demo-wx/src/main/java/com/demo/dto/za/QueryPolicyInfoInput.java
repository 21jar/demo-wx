package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QueryPolicyInfoInput extends BaseInput{

    //投保人
    private String applicantName;
    //投保人证件类型
    private String applicantCertificateType;
    //投保人证件号
    private String applicantCertificateNo;
    //保单号
    private String policyNo;

    public QueryPolicyInfoInput() {
    }

    public QueryPolicyInfoInput(String policyNo) {
        this.policyNo = policyNo;
    }
}
