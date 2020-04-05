package com.qc.service;

import com.qc.mapper.SchoolInfoMapper;
import com.qc.pojo.PagingVO;
import com.qc.pojo.SchoolInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/21 19:25
 *
 * @Description:
 */
@Service
public class SchoolService {
	@Autowired
	private SchoolInfoMapper schoolInfoMapper;

	public int save(SchoolInfo schoolInfo){
		return schoolInfoMapper.insert(schoolInfo);
	}

	public SchoolInfo getSchoolById(Integer id){
		return schoolInfoMapper.selectByPrimaryKey(id);
	}

	public int getCountSchool(Integer schoolId){
		return schoolInfoMapper.countSchool(schoolId);
	}

	public int update(SchoolInfo schoolInfo){
		return schoolInfoMapper.updateByPrimaryKeySelective(schoolInfo);
	}

	public int delete(Integer id){
		return schoolInfoMapper.deleteByPrimaryKey(id);
	}

	public List<SchoolInfo> findByPaging(Integer toPageNo){
		PagingVO pagingVO = new PagingVO();
		pagingVO.setToPageNo(toPageNo);

		List<SchoolInfo> list = schoolInfoMapper.findByPaging(pagingVO);

		return list;
	}
}
