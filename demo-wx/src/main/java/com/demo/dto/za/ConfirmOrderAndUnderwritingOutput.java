package com.demo.dto.za;

import lombok.Data;

import java.util.List;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class ConfirmOrderAndUnderwritingOutput extends BaseOutput {

    private String insureFlowCode;
    private String businessApplyNo;
    private String compelApplyNo;
    private String expiryTime;
    private String sumPreimum;
    private String businessSumPreimum;
    private String compelSumPreimum;
    private String taxPreimum;
    private String taxType;
    private Boolean success;
    private List<CoverageList> coverageList;
    private List<PayChannelList> payChannelList;

    @Data
    public static class PayChannelList {
        private String payChannelCode;
        private String payChannelName;
    }

    @Data
    public static class CoverageList {
        private String coverageCode;
        private String coveragePreimum;
    }
}
