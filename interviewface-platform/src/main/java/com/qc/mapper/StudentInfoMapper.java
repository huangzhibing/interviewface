package com.qc.mapper;

import com.qc.pojo.PagingVO;
import com.qc.pojo.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);

    int countStudent(@Param("schoolId") Integer schoolId);

    List<StudentInfo> findByPaging(@Param("pagingVO") PagingVO pagingVO,@Param("schoolId") Integer schoolId);
}