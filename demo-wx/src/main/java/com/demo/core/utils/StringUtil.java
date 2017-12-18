/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 *
 * @filename StringUtil.java
 * @package com.ratan.util
 * @author dandyzheng
 * @date 2012-6-7
 */
package com.demo.core.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;


/**
 * @author dandyzheng
 */
public class StringUtil {

    /**
     * 判断字对象是否为NULL 或者 “”
     *
     * @param object
     * @return 空：true 非空：false
     * @author dandyzheng
     * @date 2012-6-7
     */
    public static boolean isEmpty(Object object) {
        if (object == null) return true;
        if ("".equals(object.toString())) {
            return true;
        }
        return false;
    }

   

    /**
     * 将java命名转换成数据库命名，例如：userNameDs--->USER_NAME_DS
     *
     * @param key
     * @return
     * @author Administrator
     * @date 2012-8-8
     * @see
     */
    public static String makeDBName(String key) {
        if (isEmpty(key)) {
            return null;
        }
        char[] chars = key.toCharArray();
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'A' && c <= 'Z') {
                result += "_" + c;
            } else {
                result += c;
            }
        }
        return result.toUpperCase();
    }

    public static String genRandomStr(int length) {
        StringBuffer cardId = new StringBuffer(length);
        String randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        for(int i = 0; i < length; i++) {
            cardId.append(randString.charAt(r.nextInt(randString.length() - 1)));
        }
        return cardId.toString();
    }

    /**
     * 将java命名转换成数据库命名，例如：USER_NAME--->userName
     *
     * @param key
     * @return
     * @author zheng shanwei
     * @date 2012-8-9
     * @see
     */
    public static String makeEOName(String key) {

        char[] chars = key.toLowerCase().toCharArray();
        String result = "";
        int i = 0;
        while (i < chars.length) {
            char c = chars[i];
            if (c == '_') {
                result += (chars[i + 1] + "").toUpperCase();
                i++;
            } else {
                result += c;
            }
            i++;
        }
        return result;
    }

    /**
     * 从链接地址得到参数集合
     *
     * @param url bds/project/prestoreproject.xhtml?projectType=01&isDetail=01
     * @return {projectType=01,isDetail=01}
     * @author xyg
     * @date 2013-2-20
     * @see
     */
    public static List<String> getParamFromUrl(String url) {
        if (StringUtil.isEmpty(url) || url.indexOf("?") < 0) {
            return null;
        }
        String[] str = url.split("\\?");
        if (str.length < 2) {
            return null;
        }
        String paramStr = str[1];
        //得到参数字符串
        List<String> params = new ArrayList<String>();
        String[] paramArray = null;
        if (!StringUtil.isEmpty(paramStr) && paramStr.indexOf("&") > 0) {
            paramArray = paramStr.split("&");
            for (String param : paramArray) {
                if (param.indexOf("Id") < 0) {
                    //排除动态的参数
                    params.add(param);
                }
            }
        } else {
            if (paramStr.indexOf("Id") < 0) {
                params.add(paramStr);
            }
        }
        return params;
    }

    /**
     * 从链接地址获得资源路径
     *
     * @param url bds/project/prestoreproject.xhtml?projectType=01
     * @return bds/project/prestoreproject.xhtml
     * @author xyg
     * @date 2013-2-20
     * @see
     */
    public static String getResouceAddrFromUrl(String url) {
        if (StringUtil.isEmpty(url) || url.indexOf("?") < 0) {
            return url;
        }
        return url.substring(0, url.indexOf("?"));
    }


    /**
     * @return String    返回类型
     * @throws
     * @Title: getGUID
     * @Description: TODO 生成uuid{上传文件生成随机文件名}
     */
    public static String getGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 合并数组
     */
    public static String[] merge(String[] a, String[] b) {
        if (a == null && b == null)
            return null;
        if (a == null)
            return b;
        if (b == null)
            return a;
        List<String> all = new ArrayList<>();
        all.addAll(Arrays.asList(a));
        all.addAll(Arrays.asList(b));
        String[] result = new String[all.size()];
        return all.toArray(result);
    }

    public synchronized static List<String> getNanoStr(long capacity){
        Set<String> tmp = new ConcurrentSkipListSet<>();
        List<String> result = new ArrayList<>();
        while (tmp.size()<capacity){
            tmp.add(String.valueOf(System.nanoTime()).substring(1));
        }
        result.addAll(tmp);
        return result;
    }

    public static String createId()
    {
        String id = UUID.randomUUID().toString();

        id = DEKHash(id) + "";

        int diff = 12 - id.length();
        String randStr = RandomStringUtils.randomAlphabetic(12);
        for (int i = 0; i < diff; i++)
        {
            int randIndex = (int) (Math.random() * randStr.length());
            int index = (int) (Math.random() * id.length());
            id = id.substring(0, index) + randStr.charAt(randIndex) + id.substring(index, id.length());
        }
        return id;
    }

    private static int DEKHash(String str)
    {
        int hash = str.length();

        for (int i = 0; i < str.length(); i++)
        {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }

        return (hash & 0x7FFFFFFF);
    }

    public static String getMessageFormatStr(String str,Object ... arguments){
        if(isEmpty(str)){
            return str;
        }else{
            return MessageFormat.format(str,arguments);
        }
    }
}
