package com.qc.mapper;

import com.qc.pojo.EmployerInfo;
import com.qc.pojo.PagingVO;

import java.util.List;

public interface EmployerInfoMapper {
    int deleteByPrimaryKey(Integer employerId);

    int insert(EmployerInfo record);

    int insertSelective(EmployerInfo record);

    EmployerInfo selectByPrimaryKey(Integer employerId);

    int updateByPrimaryKeySelective(EmployerInfo record);

    int updateByPrimaryKey(EmployerInfo record);

	int countEmployer(Integer employerId);

	List<EmployerInfo> findByPaging(PagingVO pagingVO);
}