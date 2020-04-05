package com.qc.controller;

import com.qc.pojo.EmploymentInfo;
import com.qc.pojo.PagingVO;
import com.qc.pojo.StudentInfo;
import com.qc.pojo.Userlogin;
import com.qc.service.EmploymentService;
import com.qc.service.StudentService;
import com.qc.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
	@Autowired
	private EmploymentService employmentService;

	@RequestMapping("/personalCenter")
	public String personalCenter(String message,Model model){
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(username);
		int userId = userService.selectByUserlogin(userlogin).getUserId();
		StudentInfo student = studentService.getStudentById(userId);
		model.addAttribute("student",student);
		model.addAttribute("message",message);

		return "student/personalCenter";
	}

	@RequestMapping("/editStudentInfo")
	public String editStudentInfo(StudentInfo studentInfo,Model model){
		studentService.update(studentInfo);
		model.addAttribute("message","修改成功！");
		return "redirect:/student/personalCenter";
	}

	//修改密码
	@RequestMapping(value = "/passwordRest")
	public String passwordRest() throws Exception {
		return "student/passwordRest";
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
		list.removeIf(employmentInfo -> "已过期".equals(employmentInfo.getStatus()));

		model.addAttribute("employmentList", list);
		model.addAttribute("pagingVO", pagingVO);

		return "student/showEmployment";
	}

	//上传简历
	@ResponseBody
	@RequestMapping(value = "ajaxUploadPdf")
	public String ajaxUploadPdf(MultipartFile file){
		if (file.isEmpty()) {
			try {
				throw new Exception("文件不存在！");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "投递失败！";
		}
		return "投递成功";
	}
}
