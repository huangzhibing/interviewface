package com.qc.service;

import com.qc.mapper.StudentInfoMapper;
import com.qc.pojo.PagingVO;
import com.qc.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/21 0:17
 *
 * @Description:
 */
@Service
public class StudentService {
	@Autowired
	private StudentInfoMapper studentInfoMapper;

	public int save(StudentInfo studentInfo){
		return studentInfoMapper.insertSelective(studentInfo);
	}

	public int getCountStudent(Integer schoolId){
		return studentInfoMapper.countStudent(schoolId);
	}

	public StudentInfo getStudentById(Integer id){
		return studentInfoMapper.selectByPrimaryKey(id);
	}

	public int update(StudentInfo studentInfo){
		return studentInfoMapper.updateByPrimaryKeySelective(studentInfo);
	}

	public int delete(Integer id){
		return studentInfoMapper.deleteByPrimaryKey(id);
	}

	public List<StudentInfo> findByPaging(Integer toPageNo,Integer schoolId){
		PagingVO pagingVO = new PagingVO();
		pagingVO.setToPageNo(toPageNo);

		List<StudentInfo> list = studentInfoMapper.findByPaging(pagingVO,schoolId);

		return list;
	}
}
