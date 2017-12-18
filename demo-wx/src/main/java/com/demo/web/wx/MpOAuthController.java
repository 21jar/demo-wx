package com.demo.web.wx;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.core.utils.CookiesUtil;
import com.demo.core.weixin.WxApi;
import com.demo.core.weixin.tool.ResultCheck;
import com.demo.core.weixin.wxobj.OAuth2AccessToken;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.demo.env.WxProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.demo.core.constant.CommonConstant.OPEN_ID;
import static com.demo.core.constant.CommonConstant.OPEN_ID_LIVE_TIME;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

/**
 * @author hst on 2016/12/12
 *         <p>
 *         获取用户openid的父类控制器。
 *         所有需要在进入页面前需要获取用户openid的controller需要继承此类。
 *         并在调用控制器方法时，调用默认init.do方法。其余业务逻辑在pageInit方法中回调
 *         <p>
 *         该类有个抽象方法：pageInit，
 *         只需实现该方法就能，从参数params中获取名为 {@link com.demo.core.constant.CommonConstant}OPEN_ID 的参数就可获得相应的openid（其余自带参数也可以获取）,
 *         后续业务逻辑在该方法内正常执行
 *         <p>
 *         getOpenIdError:获取openId错误时回调，可重写
 *         *@Controller
 *         *@RequestMapping("/init.do")
 */
@Slf4j
public abstract class MpOAuthController {
    @Autowired
    private WxProperties wxProperties;
    @Autowired
    private WxApi wxApi;

    @RequestMapping("init.do")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map) throws IOException {
        log.info("call MpOAuthController init");

        String openId = CookiesUtil.getCookieValue(OPEN_ID, request);

        if (Objects.nonNull(openId)) {
            map.put(OPEN_ID, openId);
            return this.pageInit(request, response, map);
        }

        String redirectUrl = resolveRedirectUrl(request);
        String toOAuthUrl = MessageFormat.format(wxProperties.getApiUrl().getAuthCodeUrl(), redirectUrl);
        log.info("toOAuthUrl: " + toOAuthUrl);
        response.sendRedirect(toOAuthUrl);
        return null;
    }

    @RequestMapping("oAuthInit.do")
    public ModelAndView oAuthInit(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map) {
        String code = map.remove("code");
        String state = map.remove("state");
        log.info("call MpOAuthController oAuthInit code: " + code + ", state:" + state);

        OAuth2AccessToken accessToken = wxApi.getOpenId(code);

        if (ResultCheck.isSuccess(accessToken)) {
            String openId = accessToken.getOpenId();
            log.info("获取到的openId： " + openId);
            map.put(OPEN_ID, openId);
            CookiesUtil.addCookie(OPEN_ID, openId, OPEN_ID_LIVE_TIME, response);
            return this.pageInit(request, response, map);
        } else {
            return this.getOpenIdError(request, response, map, accessToken);
        }
    }

    private String resolveRedirectUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        String redirectUrl = URLEncoder.encode(UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString()).query(request.getQueryString()).build(true).encode().toUriString(), "UTF-8");
        return redirectUrl.replace("init.do", "oAuthInit.do");
    }

    protected abstract ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> params);

    protected ModelAndView getOpenIdError(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> params, BaseResult error) {
        log.error("获取openId失败：" + error);
        return new ModelAndView("redirect:static/html/wx/error.html?v=1.0.0", "error", error);
    }
}
