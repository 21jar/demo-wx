package com.demo.core.utils;

import org.apache.commons.codec.binary.Base64;


public class Base64Util {
	
	public static String EncoderBase64(String src) {
		return Base64.encodeBase64String(src.getBytes());
	}
	
	public static String EncoderBase64(byte[] src) {
		return Base64.encodeBase64String(src);
	}
	
	public static String DecoderBase64(String src) {
		return new String(Base64.decodeBase64(src));
	}
	
	public static String DecoderBase64(byte[] src) {
		return new String(Base64.decodeBase64(src));
	}
	
}
