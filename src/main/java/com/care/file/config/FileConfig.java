package com.care.file.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

//자바 파일로 빈 생성
@Configuration //설정에 관련된 내용 어노테이션
public class FileConfig {
	
	@Bean//등록하고자 하는 빈 클래스 등록
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		//1KB(1024BYTE) -> 1MB(1024KB)
		mr.setMaxUploadSize(52428800); //업로드 파일 사이즈 설정 //50MB
		mr.setDefaultEncoding("utf-8"); //인코딩 방식
		return mr;
	}
	
}
