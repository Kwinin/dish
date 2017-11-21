package com.cj.ttshop.file.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cj.ttshop.file.service.FileService;
import com.cj.ttshop.file.vo.MultipartFileParam;
import com.cj.ttshop.utils.ByteToInputStream;
import com.cj.ttshop.utils.FtpUtil;
import com.cj.ttshop.utils.IDUtils;

@Service
public class FileServiceImpl implements FileService {

	
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;

	@Override
	public Map<String, Object> uploadPicture(MultipartFileParam param) {
		// TODO Auto-generated method stub

		Map<String, Object> map = new HashMap<String, Object>();


			InputStream inputStream =ByteToInputStream.byte2Input( param.getFile());
			

			String original = param.getOriginal();
			String newName = IDUtils.genImageName();
			String type = original.substring(original.lastIndexOf("."));
			newName += type;
			String imagePath = new DateTime().toString("/yyyy/MM/dd");

			System.out.println("联网" + FTP_ADDRESS + FTP_PORT + FTP_USERNAME + FTP_PASSWORD + FTP_BASE_PATH + imagePath
					+ newName + inputStream);
			// 执行上传
			Boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
					imagePath, newName, inputStream);

			// 返回结果,如果返回值false执行if语句
			if (!result) {
				map.put("state", "ERROR");
				return map;
			}

			map.put("state", "SUCCESS");
			map.put("original", original);
			map.put("size", param.getSize());
			map.put("title", newName);
			map.put("type", type);
			map.put("url", imagePath + "/" + newName);
			return map;


	}

}
