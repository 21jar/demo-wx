package com.demo.service.wx;

import java.util.List;

import com.demo.domain.wx.HdAutoReplyRule;

/**
 * ${DESCRIPTION}
 * <p>
 * Created by hst on 2016/12/13.
 */
public interface HdAutoReplyRuleService {

    /**
     * 获取全部自动回复配置
     * @return 配置列表
     */
    List<HdAutoReplyRule> getAllAutoReplyRules();
}
