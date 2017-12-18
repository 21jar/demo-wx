package com.demo.core.weixin.wxobj.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 汽车卡加密code解码结果
 *
 * @author hst on 2017/05/02
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class CodeDecryptResult extends BaseResult {

    private String code;
}
