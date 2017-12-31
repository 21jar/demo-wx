package com.demo.web.db;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.utils.ResponseUtil;
import com.demo.core.weixin.WxApi;
import com.demo.core.weixin.wxobj.QrCode;
import com.demo.core.weixin.wxobj.result.MassSendResult;
import com.demo.core.weixin.wxobj.result.QrCodeResult;
import com.demo.domain.wx.HdUser;
import com.demo.env.WxProperties;
import com.demo.service.wx.HdUserService;


@Slf4j
@Validated
@RestController
@RequestMapping("test/")
public class TestController {

	@Autowired
	private HdUserService hdUserService;
    
    @Autowired
    private WxApi wxApi;
    
    @Autowired
    private WxProperties wxProperties;
 
    /**
     * 前端提交表单,保存数据到数据库
     * @param openId
     * @param name
     * @param mobile
     * @return
     * @author zhaoxiaobin
     * @date 2017年12月15日
     * @see
     */
    @RequestMapping("saveHdUser.do")
    public String saveHdUser(String openId,String name,String mobile) {
    	System.out.println("saveHdUser");
    	HdUser hdUser = new HdUser();
    	hdUser.setOpenId(openId);
    	hdUser.setMobile(mobile);
    	hdUser.setName(name);
    	hdUserService.saveHdUser(hdUser);
        return ResponseUtil.successToClient();
    }

	/**
	 * 生成临时带参二维码
	 * @param request
	 * @param response
	 * @param key
	 * @author zhaoxiaobin
	 * @throws IOException 
	 * @date 2017年12月15日
	 * @see
	 */
    @RequestMapping("getQrcode.do")
    public void getQrcode(HttpServletRequest request, HttpServletResponse response, String key) throws IOException {
    	System.out.println("getQrcode");
   	 	QrCode qrCode = new QrCode();
        qrCode.setActionName(QrCode.QrCodeType.QR_STR_SCENE);
         
        qrCode.setExpireSeconds(2592000);
        QrCode.Scene scene = new QrCode.Scene();
        scene.setSceneStr(key);
        qrCode.setActionInfo(new QrCode.ActionInfo(scene));
        QrCodeResult result = wxApi.createQrCode(qrCode);     
        response.sendRedirect(MessageFormat.format(wxProperties.getApiUrl().getShowQrCode(), result.getTicket()));
		
	} 
}
