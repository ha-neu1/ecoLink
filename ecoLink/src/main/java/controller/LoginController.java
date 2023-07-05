package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	//로그인
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//회원가입
	@RequestMapping("/join")
	public String join() {
		return "join";
	}
	
	//아이디 찾기
	@RequestMapping("/findId")
	public String findId() {
		return "findId";
	}
	
	//비밀번호 찾기
	@RequestMapping("/findPw")
	public String findPw() {
		return "findPw";
	}
}