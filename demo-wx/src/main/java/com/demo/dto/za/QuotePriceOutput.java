package com.demo.dto.za;

import lombok.Data;

import java.util.List;

/**
 * Created by 胡超云 on 2017/4/28.
 */

@Data
public class QuotePriceOutput extends BaseOutput {

    private String insureFlowCode;
    private String sumPreimum;
    private String businessQuoteResult;
    private String businessSumPreimum;
    private List<CoverageList> coverageList;
    private String compelQuoteResult;
    private String compelEffectiveDate;
    private String compelSumPreimum;
    private String taxPreimum;
    private String taxType;
    private String taxPayableThisYear;
    private String taxPayableLastYear;
    private String taxLateCharge;
    private String taxPayerNum;

    @Data
    public static class CoverageList {
        private String coverageCode;
        private String coveragePreimum;
    }
}
