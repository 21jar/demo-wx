package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class CreatePolicyOutput extends BaseOutput {

    private String businessPolicyNo;
    private String compelPolicyNo;
    private String businessPolicyStatus;
    private String compelPolicyStatus;
    private String businessPolicyStatusType;
    private String compelPolicyStatusType;

}
