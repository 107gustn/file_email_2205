package com.care.file.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.file.dto.FileDTO;
import com.care.file.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileMapper mapper;
	
	public void fileProcess(MultipartHttpServletRequest mul) {
		System.out.println( mul.getParameter("id") );
		System.out.println (mul.getParameter("name") );
		
		FileDTO dto = new FileDTO();
		dto.setId(mul.getParameter("id"));
		dto.setName(mul.getParameter("name"));
		
		MultipartFile file = mul.getFile("f"); //파일 변환시켜줌
		System.out.println( file.getOriginalFilename() ); //해당 파일 이름 출력
		if( file.getSize() != 0 ) { //!file.isEmpty(): 파일이 없으면 true값, 파일이 존재하면 false값 //파일이 존재하면 실행
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss-"); //날짜를 문자열로 바꿔줌
			String sysFileName = f.format(new Date());
			System.out.println(sysFileName);
			sysFileName += file.getOriginalFilename(); //파일명에 파일이름 추가
			
			dto.setImgName( sysFileName );
			
			File saveFile = new File( IMAGE_REPO + "/" + sysFileName ); //파일저장
			try {
				file.transferTo(saveFile); //해당 위치에 파일 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else { //파일을 선택하지 않았으면
			dto.setImgName("nan"); //이미지 이름이 없으면 nan값으로 처리
		}
		mapper.saveData( dto );
	}
	
	public void getData(Model model) {
		model.addAttribute("list", mapper.getData());
	}
	
	public void delete(String file, String id) {
		int result = mapper.delete(id);
		if(result == 1) { //DB에서 id가 삭제되었다면
			File d = new File(IMAGE_REPO + "/" + file); //해당이미지 파일 삭제
			d.delete();
		}
	}
	
	public void getOneData(String id, Model model) {
		model.addAttribute("dto", mapper.getOneDate(id));
	}
	
	public void modify(MultipartHttpServletRequest mul) {
		FileDTO dto = new FileDTO();
		dto.setId( mul.getParameter("id") );
		dto.setName( mul.getParameter("name") );
		
		MultipartFile m = mul.getFile("file");
		
		if( m.getSize() != 0) { //이미지 변경시 원본이미지 삭제
			File d = new File(IMAGE_REPO + "/" + mul.getParameter("origin")); //해당이미지 파일 삭제
			d.delete();
			
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss-"); //날짜를 문자열로 바꿔줌
			String sysFileName = f.format(new Date());
			System.out.println(sysFileName);
			sysFileName += m.getOriginalFilename(); //파일명에 파일이름 추가
			
			dto.setImgName( sysFileName );
			
			File saveFile = new File( IMAGE_REPO + "/" + sysFileName ); //파일저장
			
			try {
				m.transferTo(saveFile); //해당 위치에 파일 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else { //사용자가 이미지를 하지 않음
			dto.setImgName( mul.getParameter("origin") );
		}
		mapper.modify( dto );
	}
	
	public void fileProcess02(MultipartHttpServletRequest mul) {
		Iterator<String> fileNames = mul.getFileNames(); //Iterator(반복자): 여러개에 대한 파일을 배열형태로 만들어서 하나씩 꺼내옴
		while( fileNames.hasNext() ) { //hasNext: 다음값이 있는지 확인
			MultipartFile m = mul.getFile(fileNames.next());
			if(m.getSize() != 0) {
				System.out.println(m.getOriginalFilename());
				File file = new File(IMAGE_REPO + "/" + m.getOriginalFilename());
				try {
					m.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
