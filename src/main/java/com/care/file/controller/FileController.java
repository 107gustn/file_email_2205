package com.care.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.file.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	FileService fs;
	
	@GetMapping("form")
	public String form() {
		return "uploadForm";
	}
	
	@PostMapping("upload")
	/*
	public String upload(@RequestParam String id, @RequestParam String name, @RequestParam MultipartFile f) {
	*/
	public String upload(MultipartHttpServletRequest mul) { //Multipart로 넘어오는 값을 받아줄때 request
		fs.fileProcess( mul );
		return "redirect:form";
	}
	
	@GetMapping("views")
	public String views(Model model) {
		fs.getData( model );
		return "result";
	}
	
	@GetMapping("download")
	public void download(String file, HttpServletResponse response) throws Exception {
		System.out.println("file : " + file);
		response.addHeader("Content-disposition", "attachment; fileName="+file); //사용자에게 응답해줌(다운로드방식 키워드, attachment(다운로드 받을때 경로 붙여줌); 다운로드 받는 파일명)
		File f = new File(FileService.IMAGE_REPO + "/" + file); //f에 이미지 파일명 저장
		FileInputStream in = new FileInputStream(f); //해당경로로부터 읽어와서 가져옴
		
		FileCopyUtils.copy(in, response.getOutputStream()); //가져온 값을 사용자에게 얻어온값 전달
		in.close();
	}
	
	@GetMapping("delete")
	public String delete(String file, String id) {
		
		fs.delete(file, id);
		
		return "redirect:views";
	}

}
