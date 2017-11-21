package com.cj.ttshop.file.service;

import java.util.Map;

import com.cj.ttshop.file.vo.MultipartFileParam;

public interface FileService {

	Map<String,Object> uploadPicture(MultipartFileParam param);
}
