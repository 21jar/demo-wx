package com.demo.web.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.core.utils.ResponseUtil;
import com.demo.env.AutoReplyRule;

/**
 * @author hst on 2017/02/08
 * 微信关键字回复
 */
@RestController
@RequestMapping("wx/keyword/")
public class WxAutoReplyController {

    @Autowired
    private AutoReplyRule autoReplyRule;

    /**
     * User: 胡超云
     * CreateDate: 2017/6/13 13:40
     * description: 刷新关键字回复
     */
    @RequestMapping("refreshAutoReply")
    public String refreshAutoReply() {
        autoReplyRule.refreshKeywordRules();
        return "ok";
    }

    /**
     * User: 胡超云
     * CreateDate: 2017/6/13 13:41
     * description: 获取所有回复规则
     */
    @RequestMapping("getAllRule")
    public String getAllRule() {
        return ResponseUtil.successToClient(autoReplyRule.getAllRule());
    }

    /**
     * User: 胡超云
     * CreateDate: 2017/6/13 13:41
     * description: 获取回复关键字
     */
    @RequestMapping("getKeyWord")
    public String getKeyWord(@RequestParam String key) {
        return ResponseUtil.successToClient(autoReplyRule.getKeyWordRule(key));
    }

    @ExceptionHandler(Exception.class)
    public String error(Exception ex) {
        ex.printStackTrace();
        return ResponseUtil.errorToClient(ex.getMessage());
    }
}
