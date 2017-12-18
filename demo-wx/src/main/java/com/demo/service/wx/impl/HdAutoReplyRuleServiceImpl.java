package com.demo.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.core.annotation.TargetDatabase;
import com.demo.domain.wx.HdAutoReplyRule;
import com.demo.mapper.wx.HdAutoReplyRuleMapper;
import com.demo.service.wx.HdAutoReplyRuleService;

import java.util.List;

/**
 * 实现关键词自动回复
 *
 * @author hst on 2016/12/13
 */
@Service
public class HdAutoReplyRuleServiceImpl implements HdAutoReplyRuleService {
    @Autowired
    private HdAutoReplyRuleMapper hdAutoReplyRuleMapper;

    @Override
    @TargetDatabase(name = TargetDatabase.slave)
    public List<HdAutoReplyRule> getAllAutoReplyRules() {
        return hdAutoReplyRuleMapper.selectAll();
    }
}
