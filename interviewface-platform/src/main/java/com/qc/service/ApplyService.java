package com.qc.service;

import com.qc.mapper.ApplyInfoMapper;
import com.qc.pojo.ApplyInfo;
import com.qc.pojo.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huangzhibing
 * @Date: 2020/4/6 13:35
 *
 * @Description:
 */
@Service
public class ApplyService {
	@Autowired
	private ApplyInfoMapper applyInfoMapper;

	public int save(ApplyInfo applyInfo){
		return applyInfoMapper.insert(applyInfo);
	}

	public ApplyInfo getApplyById(Integer id){
		return applyInfoMapper.selectByPrimaryKey(id);
	}

	public int getCountApply(){
		return applyInfoMapper.countApply();
	}

	public int update(ApplyInfo applyInfo){
		return applyInfoMapper.updateByPrimaryKeySelective(applyInfo);
	}

	public int delete(Integer id){
		return applyInfoMapper.deleteByPrimaryKey(id);
	}

	public List<ApplyInfo> findByPaging(Integer toPageNo){
		PagingVO pagingVO = new PagingVO();
		pagingVO.setToPageNo(toPageNo);

		List<ApplyInfo> list = applyInfoMapper.findByPaging(pagingVO);

		return list;
	}

	public List<ApplyInfo> findList(ApplyInfo applyInfo){
		return applyInfoMapper.findList(applyInfo);
	}
}
