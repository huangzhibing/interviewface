package com.qc.controller;

import com.qc.pojo.StudentInfo;
import com.qc.pojo.Userlogin;
import com.qc.service.StudentService;
import com.qc.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/21 23:44
 *
 * @Description:
 */
@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private UserloginService userService;
	@Autowired
	private StudentService studentService;

	@RequestMapping("/personalCenter")
	public String personalCenter(Model model){
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(username);
		int userId = userService.selectByUserlogin(userlogin).getUserId();
		StudentInfo student = studentService.getStudentById(userId);
		model.addAttribute("student",student);

		return "student/personalCenter";
	}

	@RequestMapping("/editStudentInfo")
	public String editStudentInfo(StudentInfo studentInfo){
		studentService.update(studentInfo);
		return "redirect:/student/personalCenter";
	}

	//修改密码
	@RequestMapping(value = "/passwordRest")
	public String passwordRest() throws Exception {
		return "student/passwordRest";
	}
}
