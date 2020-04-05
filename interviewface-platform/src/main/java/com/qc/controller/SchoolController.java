package com.qc.controller;

import com.qc.pojo.PagingVO;
import com.qc.pojo.SchoolInfo;
import com.qc.pojo.StudentInfo;
import com.qc.pojo.Userlogin;
import com.qc.service.SchoolService;
import com.qc.service.StudentService;
import com.qc.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/21 23:25
 *
 * @Description:
 */
@Controller
@RequestMapping("/school")
public class SchoolController {
	@Autowired
	private UserloginService userService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private StudentService studentService;

	@RequestMapping("/personalCenter")
	public String personalCenter(Model model){
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(username);
		int userId = userService.selectByUserlogin(userlogin).getUserId();
		SchoolInfo school = schoolService.getSchoolById(userId);
		model.addAttribute("school",school);

		return "school/personalCenter";
	}

	// 查看该学校的学生信息
	@RequestMapping("/showStudent")
	public String showStudent(Model model,Integer page){
		//获取登录的学校id
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(username);
		int schoolId = userService.selectByUserlogin(userlogin).getUserId();

		List<StudentInfo> list = null;
		//页码对象
		PagingVO pagingVO = new PagingVO();
		//设置总页数
		pagingVO.setTotalCount(studentService.getCountStudent(schoolId));
		if (page == null || page == 0) {
			pagingVO.setToPageNo(1);
			list = studentService.findByPaging(1);
		} else {
			pagingVO.setToPageNo(page);
			list = studentService.findByPaging(page);
		}

		model.addAttribute("studentList", list);
		model.addAttribute("pagingVO", pagingVO);

		return "admin/showStudent";
	}
}
