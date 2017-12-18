package com.demo.core.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by 胡超云 on 2016/11/28.
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
initParams = {
        @WebInitParam(name = "exclusions", value = "*.html,*.js,*.ttf,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilterViewConfig extends WebStatFilter {
}
