package com.qc.mapper;

import com.qc.pojo.ApplyInfo;
import com.qc.pojo.PagingVO;

import java.util.List;

public interface ApplyInfoMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(ApplyInfo record);

    int insertSelective(ApplyInfo record);

    ApplyInfo selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(ApplyInfo record);

    int updateByPrimaryKey(ApplyInfo record);

	int countApply();

	List<ApplyInfo> findByPaging(PagingVO pagingVO);

	List<ApplyInfo> findList(ApplyInfo applyInfo);
}