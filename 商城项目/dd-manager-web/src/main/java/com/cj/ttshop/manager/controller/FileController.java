package com.cj.ttshop.manager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cj.ttshop.file.service.FileService;
import com.cj.ttshop.file.vo.MultipartFileParam;
import com.cj.ttshop.utils.ByteToInputStream;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	@RequestMapping(value = "/file/upload", method = RequestMethod.GET)
	@ResponseBody
	public void config(String action, HttpServletResponse response, HttpServletRequest request) throws Exception {
		System.err.println("配置文件的读取");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// response.setContentType("application/json");
		response.setContentType("text/html");// 兼容火狐
		if("config".equals(action)) {
			PrintWriter out = response.getWriter();
			
			IOUtils.copy(FileController.class.getClassLoader().getResourceAsStream("config.json"),out,"utf-8");
			
			
		}
	}
	
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> uploadImages(MultipartFile upfile, String action) throws Exception {
	
		System.out.println("我是"+action);
		if("uploadimage".equals(action)) {
			long size=upfile.getSize();
			 String original = upfile.getOriginalFilename();
			 InputStream inputsteam = upfile.getInputStream();
			 MultipartFileParam param=new MultipartFileParam();
			 
			 param.setSize(size);
			 param.setOriginal(original);
			 param.setFile(ByteToInputStream.input2byte(inputsteam));
			 
			 
			return fileService.uploadPicture(param);
		}
		return null;
	}
}
