package com.qc.controller;

import com.qc.common.FileUtils;
import com.qc.pojo.*;
import com.qc.service.ApplyService;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private ApplyService applyService;

	@RequestMapping("/personalCenter")
	public String personalCenter(String message, Model model) {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(username);
		int userId = userService.selectByUserlogin(userlogin).getUserId();
		StudentInfo student = studentService.getStudentById(userId);
		model.addAttribute("student", student);
		model.addAttribute("message", message);

		return "student/personalCenter";
	}

	@RequestMapping("/editStudentInfo")
	public String editStudentInfo(StudentInfo studentInfo, Model model) {
		studentService.update(studentInfo);
		model.addAttribute("message", "修改成功！");
		return "redirect:/student/personalCenter";
	}

	//修改密码
	@RequestMapping(value = "/passwordRest")
	public String passwordRest() throws Exception {
		return "student/passwordRest";
	}

	//招聘信息列表
	@RequestMapping("showEmployment")
	public String workStudy(Model model, Integer page) {
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

		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		Userlogin userlogin = new Userlogin();
		userlogin.setUsername(username);
		int userId = userService.selectByUserlogin(userlogin).getUserId();

		list.removeIf(employmentInfo -> "已过期".equals(employmentInfo.getStatus()));
			for (EmploymentInfo employmentInfo : list) {
			ApplyInfo applyInfo = new ApplyInfo();
			applyInfo.setEmployerId(employmentInfo.getEmployerId());
			applyInfo.setStudentId(userId);
			applyInfo.setEmploymentId(employmentInfo.getEmploymentId());
			List<ApplyInfo> applyInfoList = applyService.findList(applyInfo);
			if(applyInfoList == null || applyInfoList.size() == 0){
				continue;
			}
			String path = applyInfoList.get(applyInfoList.size() - 1).getResumePath();
			employmentInfo.setFile(path.substring(path.lastIndexOf("/")).replace("/",""));
		}

		model.addAttribute("employmentList", list);
		model.addAttribute("pagingVO", pagingVO);

		return "student/showEmployment";
	}

	//查看应聘信息列表
	@RequestMapping("showApply")
	public String showApply(Model model, Integer page) {
		List<ApplyInfo> list = null;
		//页码对象
		PagingVO pagingVO = new PagingVO();
		//设置总页数
		pagingVO.setTotalCount(applyService.getCountApply());
		if (page == null || page == 0) {
			pagingVO.setToPageNo(1);
			list = applyService.findByPaging(1);
		} else {
			pagingVO.setToPageNo(page);
			list = applyService.findByPaging(page);
		}

		for (ApplyInfo applyInfo : list) {
			EmploymentInfo employmentInfo = employmentService.getEmploymentById(applyInfo.getEmploymentId());
			applyInfo.setEmployerName(employmentInfo.getEmployerName());
			applyInfo.setPosition(employmentInfo.getPosition());
			applyInfo.setPrice(employmentInfo.getPrice());
		}

		model.addAttribute("employmentList", list);
		model.addAttribute("pagingVO", pagingVO);

		return "student/showApply";
	}

	//上传简历
	@ResponseBody
	@RequestMapping(value = "ajaxUploadPdf")
	public Map<String,Object> ajaxUploadPdf(MultipartFile file, Integer id, Integer employmentId, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();

		String path = null;
		try {
			path = FileUtils.upload(file, request);

			Subject subject = SecurityUtils.getSubject();
			String username = (String) subject.getPrincipal();
			Userlogin userlogin = new Userlogin();
			userlogin.setUsername(username);
			int userId = userService.selectByUserlogin(userlogin).getUserId();
			ApplyInfo applyInfo = new ApplyInfo();
			applyInfo.setEmployerId(id);
			applyInfo.setEmploymentId(employmentId);
			applyInfo.setStudentId(userId);
			applyInfo.setResumePath(path);
			applyService.save(applyInfo);
		} catch (IOException e) {
			e.printStackTrace();
			map.put("message","投递失败！");
			return map;
		}

		map.put("message","投递成功！");
		return map;
	}

}
