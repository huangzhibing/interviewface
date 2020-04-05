package com.qc.service;

import com.qc.mapper.EmployerInfoMapper;
import com.qc.pojo.EmployerInfo;
import com.qc.pojo.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/21 22:21
 *
 * @Description:
 */
@Service
public class EmployerService {
	@Autowired
	private EmployerInfoMapper employerInfoMapper;

	public int save(EmployerInfo employerInfo){
		return employerInfoMapper.insert(employerInfo);
	}

	public EmployerInfo getEmployerById(Integer id){
		return employerInfoMapper.selectByPrimaryKey(id);
	}

	public int getCountEmployer(Integer employerId){
		return employerInfoMapper.countEmployer(employerId);
	}

	public int update(EmployerInfo employerInfo){
		return employerInfoMapper.updateByPrimaryKeySelective(employerInfo);
	}

	public int delete(Integer id){
		return employerInfoMapper.deleteByPrimaryKey(id);
	}

	public List<EmployerInfo> findByPaging(Integer toPageNo){
		PagingVO pagingVO = new PagingVO();
		pagingVO.setToPageNo(toPageNo);

		List<EmployerInfo> list = employerInfoMapper.findByPaging(pagingVO);

		return list;
	}
}
