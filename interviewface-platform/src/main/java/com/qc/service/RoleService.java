package com.qc.service;

import com.qc.mapper.RoleMapper;
import com.qc.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/19 22:01
 *
 * @Description:
 */
@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;

	public Role findByid(Integer id) throws Exception {
		return roleMapper.selectByPrimaryKey(id);
	}

	public Role selectByRole(Role role){
		return roleMapper.selectByRole(role);
	}
}
