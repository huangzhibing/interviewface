package com.qc.service;

import com.qc.mapper.UserloginMapper;
import com.qc.pojo.Userlogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/19 22:00
 *
 * @Description:
 */
@Service
public class UserloginService {
	@Autowired
	private UserloginMapper userloginMapper;

	public Userlogin findByid(Integer id) throws Exception {
		return userloginMapper.selectByPrimaryKey(id);
	}

	public int saveUserlogin(Userlogin userlogin){
		return userloginMapper.insertSelective(userlogin);
	}

	public int removeById(Integer id){
		return userloginMapper.deleteByPrimaryKey(id);
	}

	public Userlogin selectByUserlogin(Userlogin userlogin){
		return userloginMapper.selectByUserlogin(userlogin);
	}

	public int update(Userlogin userlogin){
		return userloginMapper.updateByPrimaryKeySelective(userlogin);
	}
}
