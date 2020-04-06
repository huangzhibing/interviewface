package com.qc.common;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * @Author: huangzhibing
 * @Date: 2020/4/6 17:59
 *
 * @Description:
 */
public class FileUtils {
	/**
	 * @Author huangzhibing
	 * @Description 文件上传
	 * @Date 18:26 2020/4/6
	 * @Param [file, request]
	 * @return java.lang.String
	 **/
	public static String upload(MultipartFile file, HttpServletRequest request) throws IOException {
		//upload文件夹位置
		String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/upload");
		//原始文件名称
		String originalFileName = file.getOriginalFilename();
		// 创建年月文件夹
		Calendar date = Calendar.getInstance();
		File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH)+1));
		// 新文件
		File newFile = new File(rootPath + File.separator + dateDirs + File.separator + originalFileName);

		// 判断目标文件所在目录是否存在
		if( !newFile.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			newFile.getParentFile().mkdirs();
		}
		System.out.println(newFile);
		// 将内存中的数据写入磁盘
		file.transferTo(newFile);
		// 完整的url
		String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + originalFileName;

		return fileUrl;
	}

	public static void download(HttpServletRequest request, HttpServletResponse response,String fileUrl)throws Exception{
		//模拟文件，myfile.txt为需要下载的文件
		String fileName = request.getSession().getServletContext().getRealPath("WEB-INF/upload/" + fileUrl);
		//获取输入流
		InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
		//假如以中文名下载的话
		String filename = fileName.substring(fileName.lastIndexOf("\\"));
		//转码，免得文件名中文乱码
		filename = URLEncoder.encode(filename.replace("\\",""),"UTF-8");
		//设置文件下载头
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		while((len = bis.read()) != -1){
			out.write(len);
			out.flush();
		}
		out.close();
	}

}
