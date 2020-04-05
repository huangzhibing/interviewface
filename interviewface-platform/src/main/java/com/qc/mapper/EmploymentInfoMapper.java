package com.qc.mapper;

import com.qc.pojo.EmploymentInfo;
import com.qc.pojo.PagingVO;

import java.util.List;

public interface EmploymentInfoMapper {
    int deleteByPrimaryKey(Integer employmentId);

    int insert(EmploymentInfo record);

    int insertSelective(EmploymentInfo record);

    EmploymentInfo selectByPrimaryKey(Integer employmentId);

    int updateByPrimaryKeySelective(EmploymentInfo record);

    int updateByPrimaryKey(EmploymentInfo record);

	int countEmployment();

	List<EmploymentInfo> findByPaging(PagingVO pagingVO);
}