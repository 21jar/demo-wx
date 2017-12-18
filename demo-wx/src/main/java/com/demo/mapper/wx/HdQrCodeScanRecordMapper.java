package com.demo.mapper.wx;

import org.apache.ibatis.annotations.Mapper;

import com.demo.domain.wx.HdQrCodeScanRecord;

import java.util.List;

@Mapper
public interface HdQrCodeScanRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HdQrCodeScanRecord record);

    HdQrCodeScanRecord selectByPrimaryKey(Integer id);

    List<HdQrCodeScanRecord> selectAll();

    int updateByPrimaryKey(HdQrCodeScanRecord record);
}