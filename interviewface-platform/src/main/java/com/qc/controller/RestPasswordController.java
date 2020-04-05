package com.qc.controller;

import com.qc.pojo.Userlogin;
import com.qc.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/22 11:31
 *
 * @Description:
 */
@Controller
public class RestPasswordController {

	@Autowired
	private UserloginService userloginService;

	// 本账户密码重置
	@RequestMapping(value = "/passwordRest", method = {RequestMethod.POST})
	public String passwordRest(String oldPassword, String password1, Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(username);

		Userlogin res = userloginService.selectByUserlogin(userlogin);

		if (!oldPassword.equals(res.getPassword())) {
			model.addAttribute("message","旧密码不正确");
			return "error";
		} else {
			res.setPassword(password1);
			userloginService.update(res);
		}

		return "redirect:/logout";
	}

}
