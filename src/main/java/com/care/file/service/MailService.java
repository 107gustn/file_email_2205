package com.care.file.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	JavaMailSender mailSender;
	
	public void sendMail(String to, String subject, String body) { //받는사람, 제목, 내용
		
		MimeMessage message = mailSender.createMimeMessage(); //메세지 생성
		
		try {
			MimeMessageHelper mm = new MimeMessageHelper(message, true, "UTF-8");
			mm.setSubject(subject); //제목
			mm.setTo(to); //보내는 사람
			mm.setText(body); //내용
			mailSender.send(message); //메세지를 보냄
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail2(String to, String subject, String body) { //받는사람, 제목, 내용
		
		MimeMessage message = mailSender.createMimeMessage(); //메세지 생성
		
		try {
			MimeMessageHelper mm = new MimeMessageHelper(message, true, "UTF-8");
			mm.setSubject(subject); //제목
			mm.setTo(to); //보내는 사람
			mm.setText(body, true); //내용 //true: html 지정 형식으로 내용을 보내줌
			mailSender.send(message); //메세지를 보냄
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void auth(HttpServletRequest request) {
		HttpSession session = request.getSession(); //세션 생성
		String userid = "shushu";
		String userkey = rand();
		session.setAttribute(userid, userkey); //서버가 가지고 있는 값
		
		String body = "<h3>안녕하세요 인증입니다</h3><hr>";
		body += "<h5>" + userid + " 님</h5>";
		body += "<p>인증하기 버튼을 누르면 로그인 됩니다</p>";
		body += "<a href='http://localhost:8085"+ request.getContextPath() +"/auth_check?userid="+ userid +"&userkey=" + userkey + "'>인증하기</a>";
		sendMail2("107gustn@gmail.com", "이메일 인증", body);
	}
	
	private String rand() { //랜덤키 생성
		Random rad = new Random();
		String str = "";
		int num;
		while(str.length() != 20) { //랜덤 20문자 조합
			num = rad.nextInt(75) + 48; //0-74 = +48 => 48~122
			if( (num>=48 && num<=57) || (num>=65 && num<=90) || (num>=97 && num<=122) ) { //ASCII( (0~9) || (A~Z) || (a~z) )
				str += (char)num;
			}
		}
		return str;
	}

}
