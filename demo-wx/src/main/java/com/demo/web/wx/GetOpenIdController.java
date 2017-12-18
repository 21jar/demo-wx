package com.demo.web.wx;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.core.exception.BusinessException;
import com.demo.core.utils.CookiesUtil;
import com.demo.core.utils.ResponseUtil;
import com.demo.core.weixin.WxApi;
import com.demo.core.weixin.tool.ResultCheck;
import com.demo.core.weixin.wxobj.OAuth2AccessToken;
import com.demo.env.WxProperties;

import static com.demo.core.constant.CommonConstant.OPEN_ID;
import static com.demo.core.constant.CommonConstant.OPEN_ID_LIVE_TIME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 胡超云 on 2016/12/21.
 * 前端获取openId
 */
@Slf4j
@RestController
public class GetOpenIdController {

    @Autowired
    private WxProperties wxProperties;

    @Autowired
    private WxApi wxApi;

    @GetMapping("getOpenId.do")
    public ModelAndView getWxCode(HttpServletRequest request, String code, String toUrl, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();

        String openId = null;

        OAuth2AccessToken accessToken = wxApi.getOpenId(code);
        log.info("{}获取openId返回代码{}，信息：{}", code, accessToken.getErrCode(), accessToken.getErrMsg());
        if (ResultCheck.isSuccess(accessToken))
            openId = accessToken.getOpenId();

        if (null == openId) {
            log.info("openId获取失败code:{}", code);
            log.info("openId获取失败,重定向url{}", toUrl);
            mv.setViewName("redirect:" + toUrl);
            return mv;
        }

        CookiesUtil.addCookie(OPEN_ID, openId, OPEN_ID_LIVE_TIME, response);
        mv.setViewName("redirect:" + toUrl);
        return mv;
    }

    /** 
     * User: 胡超云
     * CreateDate: 2017/6/13 09:50
     * description: 删除openId cookie
     */
    @GetMapping("removeCookieOpenId.do")
    public String removeCookieOpenId(HttpServletResponse response){
        CookiesUtil.removeCookieOpenId(response);
        return ResponseUtil.successToClient();
    }
}
