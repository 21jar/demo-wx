package com.demo.dto.za;

import lombok.Data;

import java.util.List;

/**
 * Created by 胡超云 on 2017/4/28.
 */
@Data
public class QuerySpecialPromiseOutput extends BaseOutput {

    private String insureFlowCode;
    private String taxPrintNo;
    private List<BusinessPromiseList> businessPromiseList;
    private List<CompelPromiseList> compelPromiseList;

    @Data
    public static class BusinessPromiseList {
        private String sortIndex;
        private String promiseDesc;
    }

    @Data
    public static class CompelPromiseList {
        private String sortIndex;
        private String promiseDesc;
    }
}
