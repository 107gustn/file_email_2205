package com.care.file.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	String IMAGE_REPO = "c:/spring/image_repo";
	public void fileProcess(MultipartHttpServletRequest mul);
	public void getData(Model model);
	public void delete(String file, String id);
	
	public void getOneData(String id, Model model);
	public void modify(MultipartHttpServletRequest mul);
	
	public void fileProcess02(MultipartHttpServletRequest mul);
}
