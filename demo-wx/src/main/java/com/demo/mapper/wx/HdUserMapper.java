package com.demo.mapper.wx;

import org.apache.ibatis.annotations.Mapper;

import com.demo.domain.wx.HdUser;

@Mapper
public interface HdUserMapper {

 
    int saveHdUser(HdUser hdUser);

     
}