package com.qc.mapper;

import com.qc.pojo.PagingVO;
import com.qc.pojo.StudentInfo;
import java.util.List;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);

    int countStudent(Integer schoolId);

    List<StudentInfo> findByPaging(PagingVO pagingVO);
}