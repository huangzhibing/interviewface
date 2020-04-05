package com.qc.controller;

import com.qc.pojo.*;
import com.qc.service.EmployerService;
import com.qc.service.SchoolService;
import com.qc.service.StudentService;
import com.qc.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Jacey on 2017/6/30.
 */
@Controller
public class LoginController {
	@Autowired
	private UserloginService userloginService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private EmployerService employerService;

	//登录跳转
	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	public String loginUI(String message,Model model) throws Exception {
		model.addAttribute("message",message);
		return "../../login";
	}

	@RequestMapping(value = "/showRegister", method = {RequestMethod.GET})
	public String showRegister(String role,String message,Model model){
		Integer role1 = Integer.parseInt(role);
		if(role1 == 1){
			return "/register/registerStudent";
		}else if(role1 == 2){
			return "/register/registerSchool";
		}else if(role1 == 3){
			return "/register/registerEmployer";
		}
		return null;
	}

	//注册
	@RequestMapping(value = "/register")
	public ModelAndView register(UserloginVo vo,Model model) throws Exception {

		ModelAndView map = new ModelAndView();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(vo.getUsername());
		Userlogin userlogin2 = userloginService.selectByUserlogin(userlogin);

		if (userlogin2 != null) {
			map.addObject("message", "该用户名已存在，请换个用户名吧！");
			map.addObject("role",vo.getRole());
			map.setViewName("redirect:/showRegister");
			return map;
		}

		userlogin.setStatus(0);
		userlogin.setRole(vo.getRole());
		userlogin.setPassword(vo.getPassword());
		userloginService.saveUserlogin(userlogin);
		int userId = userlogin.getUserId();

		if (vo.getRole() == 1) {
			StudentInfo student = new StudentInfo();
			BeanUtils.copyProperties(vo,student);
			student.setStatus(0);
			student.setStudentName(userlogin.getUsername());
			student.setStudentId(userId);

			//todo 还要做学校id传到后台的操作
			studentService.save(student);
			map.addObject("message", "注册成功，请等待学校认证通过后登录！");
			map.setViewName("redirect:/login");
		} else if (vo.getRole() == 2) {
			//学校
			SchoolInfo schoolInfo = new SchoolInfo();
			BeanUtils.copyProperties(vo,schoolInfo);
			schoolInfo.setStatus(0);
			schoolInfo.setSchoolName(userlogin.getUsername());
			schoolInfo.setSchoolId(userId);

			schoolService.save(schoolInfo);
		} else {
			//招聘方
			EmployerInfo employerInfo = new EmployerInfo();
			employerInfo.setStatus(0);
			employerInfo.setEmployerName(userlogin.getUsername());
			employerInfo.setEmployerId(userId);
			BeanUtils.copyProperties(vo,employerInfo);

			employerService.save(employerInfo);
		}
		map.addObject("message", "注册成功，请等待管理员认证通过后登录！");
		map.setViewName("redirect:/login");
		return map;
	}

	//登录表单处理
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public String login(Userlogin userlogin, Model model) throws Exception {
		Userlogin selectUser = userloginService.selectByUserlogin(userlogin);
		if(selectUser == null){
			model.addAttribute("message", "用户名或密码错误");
			return "redirect:/login";
		}
		if(selectUser.getStatus() == null || selectUser.getStatus() == 0){
			model.addAttribute("message", "账号未激活");
			return "redirect:/login";
		}

		//Shiro实现登录
		UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(), userlogin.getPassword());
		Subject subject = SecurityUtils.getSubject();

		//如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
		subject.login(token);
		if (subject.hasRole("admin")) {
			return "redirect:/admin/showStudent";
		} else if (subject.hasRole("student")) {
			return "redirect:/student/personalCenter";
		} else if (subject.hasRole("school")) {
			return "redirect:/school/personalCenter";
		} else if (subject.hasRole("employer")) {
			return "redirect:/employer/personalCenter";
		}

		return "/login";
	}

}
