package com.demo.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.core.annotation.TargetDatabase;
import com.demo.domain.wx.HdUser;
import com.demo.mapper.wx.HdUserMapper;
import com.demo.service.wx.HdUserService;

/**
 * 实现关键词自动回复
 *
 * @author hst on 2016/12/13
 */
@Service
public class HdUserServiceImpl implements HdUserService {
    @Autowired
    private HdUserMapper hdUserMapper;

    @Override
    @Transactional
    @TargetDatabase(name = TargetDatabase.master)
    public boolean saveHdUser(HdUser hdUser ) {
        hdUserMapper.saveHdUser(hdUser);
        return true;
    }
}
