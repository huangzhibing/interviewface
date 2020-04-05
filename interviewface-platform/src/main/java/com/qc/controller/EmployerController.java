package com.qc.controller;

import com.qc.pojo.EmployerInfo;
import com.qc.pojo.Userlogin;
import com.qc.service.EmployerService;
import com.qc.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/21 23:45
 *
 * @Description:
 */
@Controller
@RequestMapping("/employer")
public class EmployerController {
	@Autowired
	private UserloginService userService;
	@Autowired
	private EmployerService employerService;

	@RequestMapping("/personalCenter")
	public String personalCenter(Model model){
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(username);
		int userId = userService.selectByUserlogin(userlogin).getUserId();
		EmployerInfo employer = employerService.getEmployerById(userId);
		model.addAttribute("employer",employer);

		return "employer/personalCenter";
	}
}
