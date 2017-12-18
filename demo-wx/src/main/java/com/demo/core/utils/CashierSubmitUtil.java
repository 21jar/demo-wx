package com.demo.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.demo.core.constant.CashierConstant;


public class CashierSubmitUtil {
    /**
     * @Title: buildPostRequest
     * @Description: 建立请求，以表单HTML形式构造
     * @param action 众安收银台网关地址
     * @param sParaTemp 提交参数，还未签名
     * @param appKey MD5 key
     * @return String
     */
    public static String buildPostRequest(String action, Map<String, String> sParaTemp, String appKey) {
        //签名
        Map<String, String> requestMap = CashierMD5Util.buildRequestPara(sParaTemp, appKey);
        List<String> keys = new ArrayList<String>(requestMap.keySet());
        String chartSet = sParaTemp.get(CashierConstant.REQUEST_CHARSET);

        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<html>");
        sbHtml.append("<form id=\"cashierpaysubmit\" accept-charset=\""+chartSet+"\" name=\"alipaysubmit\" action=\"" + action + "\" method=\"post\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) requestMap.get(name);
            try {
				value = URLEncoder.encode(value, chartSet);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value='" + value + "\'/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"提交\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['cashierpaysubmit'].submit();</script>");
        sbHtml.append("</html>");
        return sbHtml.toString();
    }
    
    /**
    * @Title: buildGetRequest
    * @Description: 建立请求，以url形式构造
    * @param action
    * @param sParaTemp
    * @param appKey
    * @return String
    */ 
    public static String buildGetRequest(String action, Map<String, String> sParaTemp, String appKey) {
        //签名
        Map<String, String> requestMap = CashierMD5Util.buildRequestPara(sParaTemp, appKey);
        List<String> keys = new ArrayList<String>(requestMap.keySet());
        String chartSet = sParaTemp.get(CashierConstant.REQUEST_CHARSET);

        StringBuffer sbUrl = new StringBuffer();
        sbUrl.append(action).append("?");
        boolean isFirst = true;
        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) requestMap.get(name);
            try {
				value = URLEncoder.encode(value, chartSet);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            if (!isFirst) {
            	sbUrl.append("&");
            } else {
            	isFirst = false;
            }
            sbUrl.append(name).append("=").append(value);
        }

        return sbUrl.toString();
    }
}
