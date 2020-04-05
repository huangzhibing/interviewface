package com.qc.mapper;

import com.qc.pojo.CampusInfo;

public interface CampusInfoMapper {
    int deleteByPrimaryKey(Integer recruitmentId);

    int insert(CampusInfo record);

    int insertSelective(CampusInfo record);

    CampusInfo selectByPrimaryKey(Integer recruitmentId);

    int updateByPrimaryKeySelective(CampusInfo record);

    int updateByPrimaryKey(CampusInfo record);
}