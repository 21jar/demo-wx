package com.demo.mapper.wx;

import org.apache.ibatis.annotations.Mapper;

import com.demo.domain.wx.HdMenuConfig;

import java.util.List;

@Mapper
public interface HdMenuConfigMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HdMenuConfig record);

    HdMenuConfig selectByPrimaryKey(Integer id);

    List<HdMenuConfig> selectAll();

    int updateByPrimaryKey(HdMenuConfig record);

    int updateWxMenuId(Integer localMenuId, String wxMenuId);
}