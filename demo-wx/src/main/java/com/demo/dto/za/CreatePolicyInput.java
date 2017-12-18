package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class CreatePolicyInput extends BaseInput{

    //商业险投保单号
    private String businessApplyNo;
    //交强险投保单号
    private String compelApplyNo;
    //收银台交易流水号
    private String payTradeNo;
    //商户唯一订单号
    private String outTradeNo;
    //商户唯一订单号
    private String tradeNo;

    public CreatePolicyInput(){

    }

    public CreatePolicyInput(String businessApplyNo, String tradeNo, String outTradeNo, String compelApplyNo, String payTradeNo) {
        this.businessApplyNo = businessApplyNo;
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.compelApplyNo = compelApplyNo;
        this.payTradeNo = payTradeNo;
    }
}
