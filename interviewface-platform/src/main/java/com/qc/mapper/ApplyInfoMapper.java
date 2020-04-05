package com.qc.mapper;

import com.qc.pojo.ApplyInfo;

public interface ApplyInfoMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(ApplyInfo record);

    int insertSelective(ApplyInfo record);

    ApplyInfo selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(ApplyInfo record);

    int updateByPrimaryKey(ApplyInfo record);
}