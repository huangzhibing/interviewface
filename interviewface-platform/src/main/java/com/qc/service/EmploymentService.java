package com.qc.service;

import com.qc.mapper.EmploymentInfoMapper;
import com.qc.pojo.EmploymentInfo;
import com.qc.pojo.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huangzhibing
 * @Date: 2020/4/5 14:37
 *
 * @Description:
 */
@Service
public class EmploymentService {
	@Autowired
	private EmploymentInfoMapper employmentInfoMapper;

	public int save(EmploymentInfo employmentInfo){
		return employmentInfoMapper.insert(employmentInfo);
	}

	public EmploymentInfo getEmploymentById(Integer id){
		return employmentInfoMapper.selectByPrimaryKey(id);
	}

	public int getCountEmployment(){
		return employmentInfoMapper.countEmployment();
	}

	public int update(EmploymentInfo employmentInfo){
		return employmentInfoMapper.updateByPrimaryKeySelective(employmentInfo);
	}

	public int delete(Integer id){
		return employmentInfoMapper.deleteByPrimaryKey(id);
	}

	public List<EmploymentInfo> findByPaging(Integer toPageNo){
		PagingVO pagingVO = new PagingVO();
		pagingVO.setToPageNo(toPageNo);

		List<EmploymentInfo> list = employmentInfoMapper.findByPaging(pagingVO);

		return list;
	}
}
