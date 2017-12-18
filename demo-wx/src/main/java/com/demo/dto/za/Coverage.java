package com.demo.dto.za;

import lombok.Data;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class Coverage {

    //险别性质
    private String baseRiderType;
    //所属主险编码
    private String parentCoverageCode;
    //险别代码
    private String coverageCode;
    //险别名称
    private String coverageName;
    //是否不计免赔
    private String isNonDeductible;
    //保额
    private String sumInsured;
    //玻璃类型
    private String glassType;

    public Coverage() {
    }

    public Coverage(String baseRiderType, String parentCoverageCode, String coverageCode, String coverageName, String isNonDeductible, String sumInsured, String glassType) {
        this.baseRiderType = baseRiderType;
        this.parentCoverageCode = parentCoverageCode;
        this.coverageCode = coverageCode;
        this.coverageName = coverageName;
        this.isNonDeductible = isNonDeductible;
        this.sumInsured = sumInsured;
        this.glassType = glassType;
    }
}
