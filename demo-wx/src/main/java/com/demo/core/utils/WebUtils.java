package com.demo.core.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

public class WebUtils extends org.springframework.web.util.WebUtils{
	private static final String HTTP_REAL_REQUEST_URI = "X-Real-Request-URI";
	private static final String HTTP_REAL_URL = "X-Real-URL";
	
	public static String getRealIp(HttpServletRequest request) {  
	    String ip = request.getHeader("X-Real-IP");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("X-Forwarded-For");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	} 
	
	public static String requestUri(HttpServletRequest request){
		String ret = request.getHeader(HTTP_REAL_REQUEST_URI);
		if(ret == null) ret = request.getRequestURI();
		return ret;
	}
	
	public static String requestFullUrl(HttpServletRequest request){
		String ret = request.getHeader(HTTP_REAL_URL);
		if(ret == null){
			String q = request.getQueryString();
			ret = request.getRequestURL().append(q!=null?"?"+q:"").toString();
		}
		return ret;
	}
	
	public static String requestContextPath(HttpServletRequest request){
		String realUri = request.getHeader(HTTP_REAL_REQUEST_URI);
		if(realUri == null) return request.getContextPath();
		int pos = realUri.lastIndexOf(request.getServletPath()+request.getPathInfo());
		if(pos > 0) return realUri.substring(0, pos);
		return "";
	}

	public static String requestParametersAny(HttpServletRequest request,String... names){
		for(String name : names) 
			if(request.getParameter(name)!=null) 
				return request.getParameter(name);
		return null;
	}
	
	
	public static HttpEntity<String> entityResponse(String body, MediaType contentType){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(contentType);
		return new HttpEntity<String>(body,headers);
	}

	public static HttpEntity<String> jsonResponse(String json){
		return entityResponse(json,MediaType.valueOf("application/json;charset=UTF-8"));
	}

	public static HttpEntity<String> xmlResponse(String xml){
		return entityResponse(xml,MediaType.valueOf("text/xml;charset=UTF-8"));
	}
}
