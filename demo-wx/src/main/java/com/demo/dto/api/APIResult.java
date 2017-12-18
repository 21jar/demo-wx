package com.demo.dto.api;

import lombok.Data;

/**
 * 
 * @author zhongjiafeng
 * @date 2017年11月23日
 */
@Data
public class APIResult {
	public static final String SUCCESS = "0";
	
	private String errCode;
	private String errMsg;
}
