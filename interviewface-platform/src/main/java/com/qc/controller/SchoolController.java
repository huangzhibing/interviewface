package com.qc.controller;

import com.qc.pojo.*;
import com.qc.service.EmploymentService;
import com.qc.service.SchoolService;
import com.qc.service.StudentService;
import com.qc.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@Autowired
	private EmploymentService employmentService;

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

	@RequestMapping("/editSchoolInfo")
	public String editSchoolInfo(SchoolInfo school,Model model){

		schoolService.update(school);
		model.addAttribute("message","修改成功！");

		return "redirect:/school/personalCenter";
	}

	@RequestMapping("/passStudent")
	public String passStudent(Integer id,Model model){
		StudentInfo studentInfo = studentService.getStudentById(id);
		studentInfo.setStatus(1);
		studentService.update(studentInfo);

		int userId = studentInfo.getSchoolId();
		Userlogin userlogin = new Userlogin();
		userlogin.setUserId(userId);
		Userlogin userlogin1 = userService.selectByUserlogin(userlogin);
		userService.update(userlogin1);

		model.addAttribute("message","审核通过成功！");

		return "redirect:/school/showStudentBySchool";
	}

	@RequestMapping("/noPassStudent")
	public String noPassStudent(Integer id,Model model){
		StudentInfo studentInfo = studentService.getStudentById(id);
		studentInfo.setStatus(2);
		studentService.update(studentInfo);

		int userId = studentInfo.getSchoolId();
		Userlogin userlogin = new Userlogin();
		userlogin.setUserId(userId);
		Userlogin userlogin1 = userService.selectByUserlogin(userlogin);
		userService.update(userlogin1);

		model.addAttribute("message","审核不通过成功！");

		return "redirect:/school/showStudentBySchool";
	}

	// 查看该学校的学生信息
	@RequestMapping("/showStudentBySchool")
	public String showStudentBySchool(Model model,Integer page){
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
			list = studentService.findByPaging(1,schoolId);
		} else {
			pagingVO.setToPageNo(page);
			list = studentService.findByPaging(page,schoolId);
		}

		model.addAttribute("studentList", list);
		model.addAttribute("pagingVO", pagingVO);

		return "school/showStudent";
	}

	//招聘信息列表
	@RequestMapping("showEmployment")
	public String workStudy(Model model,Integer page){
		List<EmploymentInfo> list = null;
		//页码对象
		PagingVO pagingVO = new PagingVO();
		//设置总页数
		pagingVO.setTotalCount(employmentService.getCountEmployment());
		if (page == null || page == 0) {
			pagingVO.setToPageNo(1);
			list = employmentService.findByPaging(1);
		} else {
			pagingVO.setToPageNo(page);
			list = employmentService.findByPaging(page);
		}

		model.addAttribute("employmentList", list);
		model.addAttribute("pagingVO", pagingVO);

		return "school/showEmployment";
	}

	// 添加招聘信息操作
	@RequestMapping(value = "/addEmployment", method = {RequestMethod.GET})
	public String addEmploymentUI() throws Exception {
		return "/school/addEmployment";
	}

	// 添加招聘信息操作
	@RequestMapping(value = "/addEmployment", method = {RequestMethod.POST})
	public String addEmployment(EmploymentInfo employmentInfo, Model model) throws Exception {

		employmentInfo.setStatus("招聘中");
		int result = employmentService.save(employmentInfo);

		if (result == 0) {
			model.addAttribute("message", "发布失败！");
		}else {
			model.addAttribute("message","发布成功！");
		}

		//重定向
		return "redirect:/school/showEmployment";
	}

	@RequestMapping(value = "removeEmployment")
	public String removeEmployment(String id,Model model){
		EmploymentInfo employmentInfo = employmentService.getEmploymentById(Integer.parseInt(id));
		employmentInfo.setStatus("已过期");
		if(employmentService.update(employmentInfo) != 0){
			model.addAttribute("过期成功！");
		}
		return "redirect:/school/showEmployment";
	}

}
