package com.qc.controller;

import com.qc.pojo.*;
import com.qc.service.EmployerService;
import com.qc.service.SchoolService;
import com.qc.service.StudentService;
import com.qc.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/20 23:46
 *
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserloginService userloginService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private EmployerService employerService;

	// 学生信息显示
	@RequestMapping("/showStudent")
	public String showStudent(Model model,Integer page){
		List<StudentInfo> list = null;
		//页码对象
		PagingVO pagingVO = new PagingVO();
		//设置总页数
		pagingVO.setTotalCount(studentService.getCountStudent(null));
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

	// 学校信息显示
	@RequestMapping("/showSchool")
	public String showSchool(Model model,Integer page){
		List<SchoolInfo> list = null;
		//页码对象
		PagingVO pagingVO = new PagingVO();
		//设置总页数
		pagingVO.setTotalCount(schoolService.getCountSchool(null));
		if (page == null || page == 0) {
			pagingVO.setToPageNo(1);
			list = schoolService.findByPaging(1);
		} else {
			pagingVO.setToPageNo(page);
			list = schoolService.findByPaging(page);
		}

		model.addAttribute("schoolList", list);
		model.addAttribute("pagingVO", pagingVO);

		return "admin/showSchool";
	}

	// 招聘方信息显示
	@RequestMapping("/showEmployer")
	public String showEmployer(Model model,Integer page){
		List<EmployerInfo> list = null;
		//页码对象
		PagingVO pagingVO = new PagingVO();
		//设置总页数
		pagingVO.setTotalCount(employerService.getCountEmployer(null));
		if (page == null || page == 0) {
			pagingVO.setToPageNo(1);
			list = employerService.findByPaging(1);
		} else {
			pagingVO.setToPageNo(page);
			list = employerService.findByPaging(page);
		}

		model.addAttribute("employerList", list);
		model.addAttribute("pagingVO", pagingVO);

		return "admin/showEmployer";
	}

	@RequestMapping("/activationStu")
	public String activationStu(StudentInfo studentInfo,Model model){
//		tudentService.updataById(studentCustom.getUserid(), studentCustom);

		return "redirect:/admin/showStudent";
	}

	// 删除学生
	@RequestMapping(value = "/removeStudent", method = {RequestMethod.GET} )
	private String removeStudent(Integer id) throws Exception {
		if (id == null) {
			//加入没有带学生id就进来的话就返回学生显示页面
			return "admin/showStudent";
		}
		studentService.delete(id);
		userloginService.removeById(id);

		return "redirect:/admin/showStudent";
	}

	// 删除学校
	@RequestMapping(value = "/removeSchool", method = {RequestMethod.GET} )
	private String removeSchool(Integer id) throws Exception {
		if (id == null) {
			//加入没有带学生id就进来的话就返回学生显示页面
			return "admin/showSchool";
		}
		schoolService.delete(id);
		userloginService.removeById(id);

		return "redirect:/admin/showSchool";
	}

	// 删除招聘方
	@RequestMapping(value = "/removeEmployer", method = {RequestMethod.GET} )
	private String removeEmployer(Integer id) throws Exception {
		if (id == null) {
			//加入没有带学生id就进来的话就返回学生显示页面
			return "admin/showEmployer";
		}
		employerService.delete(id);
		userloginService.removeById(id);

		return "redirect:/admin/showEmployer";
	}


	// 修改学生信息页面显示
	@RequestMapping(value = "/editStudent", method = {RequestMethod.GET})
	public String editStudentUI(Integer id, Model model) throws Exception {
		if (id == null) {
			//加入没有带学生id就进来的话就返回学生显示页面
			return "redirect:/admin/showStudent";
		}
		StudentInfo studentCustom = studentService.getStudentById(id);
		if (studentCustom == null) {
			throw new Exception("未找到该名学生");
		}

//		model.addAttribute("collegeList", list);
		model.addAttribute("student", studentCustom);


		return "admin/editStudent";
	}

	// 修改学校信息页面显示
	@RequestMapping(value = "/editSchool", method = {RequestMethod.GET})
	public String editSchoolUI(Integer id, Model model) throws Exception {
		if (id == null) {
			//加入没有带学生id就进来的话就返回学生显示页面
			return "redirect:/admin/showSchool";
		}
		SchoolInfo schoolInfo = schoolService.getSchoolById(id);
		if (schoolInfo == null) {
			throw new Exception("未找到该学校");
		}

		model.addAttribute("school", schoolInfo);


		return "admin/editSchool";
	}

	// 修改招聘方信息页面显示
	@RequestMapping(value = "/editEmployer", method = {RequestMethod.GET})
	public String editEmployerUI(Integer id, Model model) throws Exception {
		if (id == null) {
			//加入没有带学生id就进来的话就返回学生显示页面
			return "redirect:/admin/showEmployer";
		}
		EmployerInfo employerInfo = employerService.getEmployerById(id);
		if (employerInfo == null) {
			throw new Exception("未找到该招聘方");
		}

		model.addAttribute("employer", employerInfo);


		return "admin/editEmployer";
	}


	//修改学生信息处理
	@ResponseBody
	@RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
	public Map<String,Object> editStudent(StudentInfo studentCustom) throws Exception {
		Map<String,Object> map = new HashMap<>();

		studentService.update(studentCustom);

		//重定向
		return map;
	}

	//修改学生信息处理
	@ResponseBody
	@RequestMapping(value = "/editSchool", method = {RequestMethod.POST})
	public Map<String,Object> editSchool(SchoolInfo schoolInfo) throws Exception {
		Map<String,Object> map = new HashMap<>();

		if(!StringUtils.isEmpty(schoolInfo.getType())){
			if("save".equals(schoolInfo.getType())){

			}else if("pass".equals(schoolInfo.getType())){
				schoolInfo.setStatus(1);
				Userlogin userlogin = userloginService.findByid(schoolInfo.getSchoolId());
				userlogin.setStatus(1);
				userloginService.update(userlogin);
				map.put("message","审核通过！");
			}else {
				schoolInfo.setStatus(2);
				map.put("message","审核不通过！");
			}
		}
		schoolService.update(schoolInfo);

		//重定向
		return map;
	}

	//修改招聘方信息处理
	@ResponseBody
	@RequestMapping(value = "/editEmployer", method = {RequestMethod.POST})
	public Map<String,Object> editEmployer(EmployerInfo employerInfo) throws Exception {
		Map<String,Object> map = new HashMap<>();

		if(!StringUtils.isEmpty(employerInfo.getType())){
			if("save".equals(employerInfo.getType())){

			}else if("pass".equals(employerInfo.getType())){
				employerInfo.setStatus(1);
				Userlogin userlogin = userloginService.findByid(employerInfo.getEmployerId());
				userlogin.setStatus(1);
				userloginService.update(userlogin);
				map.put("message","审核通过！");
			}else {
				employerInfo.setStatus(2);
				map.put("message","审核不通过！");
			}
		}
		employerService.update(employerInfo);

		return map;
	}

}
