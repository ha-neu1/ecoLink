package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import dto.EnterpriseDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletResponse;
import service.MyInfoService;

@Controller
public class MyInfoController {
	@Autowired
	MyInfoService service;
	
	//유저 정보 조회
	@GetMapping("/userInfo")
    public ModelAndView myInfo(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		/*
		 * System.out.println("myInfo getMemId : " + dto.getMemId()); MemberDTO
		 * loginuser = service.getUser(dto.getMemId());
		 */
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		
		if(dto.getMemType().equals("enter")) {
			EnterpriseDTO edto = service.getEntUser(dto.getMemId());
			mv.addObject("loginUser", dto);
			mv.addObject("loginEnt", edto);
			mv.setViewName("EntInfo");
		}
		else {
			mv.addObject("loginUser", dto);
			mv.setViewName("MyInfo");
		}
		
		return mv;
    }
	
	//유저 정보 수정	
	@GetMapping("/updateUserInfo")
	public ModelAndView myInfoupdate(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		
		if(dto.getMemType().equals("enter")) {
			EnterpriseDTO edto = service.getEntUser(dto.getMemId());
			mv.addObject("loginUser", dto);
			mv.addObject("loginEnt", edto);
			mv.setViewName("EntInfoUpdate");
		} else {
			mv.addObject("loginUser", dto);
			mv.setViewName("MyInfoUpdate");
		}
		return mv;
	}
	
	@PostMapping("/updateUserInfo")
	public @ResponseBody String myInfoupdatesql(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if(dto.getMemType().equals("enter")) {
//			EnterpriseDTO edto = service.getEntUser(dto.getMemId());
//			service.userUpdate(dto);
//			service.entUpdate(edto);
		} else {
			service.userUpdate(dto);
		}
		return "";
	}
	
	//유저 삭제
	@RequestMapping("/deleteMember")
    public String deleteMember(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		String memId = dto.getMemId();
		service.deleteUser(memId);
        return "";
    }
	
	@RequestMapping("/entInfo")
    public String entInfo() {
        return "EntInfo";
    }
	
	@RequestMapping("/updateEntInfo")
    public String updateEntInfo() {
        return "EntInfoUpdate";
    }
	

}
