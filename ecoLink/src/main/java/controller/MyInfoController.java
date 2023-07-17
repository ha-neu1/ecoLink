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
	@GetMapping("/myInfo")
    public ModelAndView myInfo(HttpSession session) {
		MemberDTO loginuser = service.getUser((String)session.getAttribute("memId"));
		ModelAndView mv = new ModelAndView();
		mv.addObject("loginUser", loginuser);
		mv.setViewName("MyInfo");
		return mv;
    }
	
	//유저 정보 수정	
	@GetMapping("/updateMyInfo")
	public ModelAndView myInfoupdate(HttpSession session) {
		MemberDTO loginuser = service.getUser((String)session.getAttribute("memId"));
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
    public String deleteMember(HttpSession session) {
		String memId = (String) session.getAttribute("memId");
		service.deleteMember(memId);
        return "";
    }
	

}
