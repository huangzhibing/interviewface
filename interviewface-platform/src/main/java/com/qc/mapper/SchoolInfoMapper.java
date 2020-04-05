package com.qc.mapper;

import com.qc.pojo.PagingVO;
import com.qc.pojo.SchoolInfo;

import java.util.List;

public interface SchoolInfoMapper {
    int deleteByPrimaryKey(Integer schoolId);

    int insert(SchoolInfo record);

    int insertSelective(SchoolInfo record);

    SchoolInfo selectByPrimaryKey(Integer schoolId);

    int updateByPrimaryKeySelective(SchoolInfo record);

    int updateByPrimaryKey(SchoolInfo record);

	List<SchoolInfo> findByPaging(PagingVO pagingVO);

	int countSchool(Integer schoolId);
}