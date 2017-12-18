package com.demo.web.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.utils.ResponseUtil;
import com.demo.core.weixin.wxobj.AccessToken;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.demo.service.wx.WxApiService;
import com.demo.service.wx.WxMenuConfigService;

/**
 * 微信api调用接口
 * 微信相关接口
 * @author hst on 2016/12/22
 */
@RestController
@RequestMapping(value = "wx/api/")
public class WxApiController {

    @Autowired
    private WxMenuConfigService wxMenuConfigService;
    @Autowired
    private WxApiService wxApiService;

    /**
     * User: 胡超云
     * CreateDate: 2017/6/13 09:52
     * description: 获取accessToken
     */
    @RequestMapping(value = "accessToken")
    public AccessToken accessToken() {
        return wxApiService.accessToken();
    }

    /**
     * User: 胡超云
     * CreateDate: 2017/6/13 09:53
     * description: 更新微信公众号菜单
     */
    @RequestMapping(value = "updateWxMenu")
    public List<BaseResult> updateWxMenu() {
        return wxMenuConfigService.updateWxMenu();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String error(Exception e) {
        return ResponseUtil.errorToClient(e.getMessage());
    }
}
