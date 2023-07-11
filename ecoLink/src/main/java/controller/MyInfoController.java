package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import service.MyInfoService;

@Controller
public class MyInfoController {
	@Autowired
	MyInfoService service;
	
	//유저 정보 조회
	@RequestMapping("/myInfo")
    public String myInfo() {
        return "MyInfo";
    }
	
	//유저 정보 수정
//	@RequestMapping("/updateMyInfo")
//    public String updateUser() {
//        return "MyInfoUpdate";
//    }
	
	@GetMapping("/updateMyInfo")
	public ModelAndView myInfoupdate(HttpSession session) {
		MemberDTO loginuser = service.getUser((String) session.getAttribute("memId"));
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("loginUser", loginuser);
		mv.setViewName("MyInfoUpdate");
		return mv;
	}
	
	@PostMapping("/updateMyInfo")
	public @ResponseBody String myInfoupdatesql(MemberDTO dto) {
		service.memberUpdate(dto);
		return "";
	}
	
	
	//유저 삭제
	@RequestMapping("/deleteMember")
    public String deleteMember() {
        return "";
    }
	
	//브랜드 북마크 조회
	//@GetMapping("")
	//public  {
		
	//}
	
	//좋아요한 글 조회
	//@GetMapping("/")
	
	//내가 쓴 글 조회
	//@GetMapping("/")

}
