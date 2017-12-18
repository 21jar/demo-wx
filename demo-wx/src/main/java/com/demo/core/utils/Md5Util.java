package com.demo.core.utils;/**
 * Created by 胡超云 on 2017/5/22.
 */

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: 胡超云
 * CreateDate： 2017-05-22 13:36
 * description: 加密工具类
 * version: 1.0.0
 */
public class Md5Util {

    //SHA-256加密
    public static String getEncryptStr(String str){

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(str.getBytes("UTF-8"));
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String EncoderByMd52(String str){
        StringBuffer md5Str = new StringBuffer("");
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] b = md5.digest(str.getBytes("utf-8"));
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16){
                    md5Str.append("0");
                }
                md5Str.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5Str.toString();
    }
}
