package com.demo.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 胡超云 on 2016/11/17.
 */
public class CookiesUtil {

    /**
     * 删除openId
     * @param response
     */
    public static void removeCookieOpenId(HttpServletResponse response){
        addCookie("open_id", null, 0, response);
    }

    /**
     * 添加cookie
     *
     * @param cookieName
     * @param data
     * @param expires
     * @param response
     */
    public static void addCookie(String cookieName, String data, int expires, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, data);
        cookie.setMaxAge(expires);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param cookieName
     * @param request
     * @return
     */
    public static String getCookieValue(String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String vlaue = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                if (c.getName().equalsIgnoreCase(cookieName)) {
                    vlaue = c.getValue();
                    break;
                }
            }
        }
        return vlaue;
    }
}
