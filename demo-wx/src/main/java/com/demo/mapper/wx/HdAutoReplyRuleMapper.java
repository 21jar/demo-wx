package com.demo.mapper.wx;

import org.apache.ibatis.annotations.Mapper;

import com.demo.domain.wx.HdAutoReplyRule;

import java.util.List;

@Mapper
public interface HdAutoReplyRuleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HdAutoReplyRule record);

    HdAutoReplyRule selectByPrimaryKey(Integer id);

    List<HdAutoReplyRule> selectAll();

    int updateByPrimaryKey(HdAutoReplyRule record);
}