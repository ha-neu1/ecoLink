package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.EnterpriseDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;

@Controller
public class MemberController {

	@Autowired
    private MemberService service;
	
	//로그인
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
	//로그인	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response, HttpServletRequest request, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		MemberDTO dto = service.login(memberDTO);
		if (dto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("logininfo", dto);
			return "redirect:/main";
		} else {
			model.addAttribute("loginfail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "login";
		}
	}

    // 로그아웃
	@PostMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }

	    return "login";
	}
    
    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String memberjoin(@ModelAttribute("member") MemberDTO member, @ModelAttribute("enter") EnterpriseDTO enter) {
      service.addMember(member); // addMember 호출

      if (member.getMemType().equals("기업회원")) {
        service.addEnterprise(enter.getEntCrn(), enter.getEntPhone()); // addEnterprise 호출
      }

      return "redirect:/login";
    }


    
    //id, email 중복여부
    @RequestMapping(value="/ismemberexist", produces = {"application/json;charset=utf-8"})
    public @ResponseBody String isMemberexist(@RequestParam("inputId") String inputId, @RequestParam("inputEmail") String inputEmail) {
    	int idresult = service.isMemberIdExist(inputId);
    	int emailresult = service.isMemberEmailExist(inputEmail);
    	String result = "";
    	
    	if( idresult == 0 && emailresult == 0) {
    		result = "ok";
    	}
    	else if(idresult == 1 && emailresult == 0) {
    		result = "one_id";
    	}
    	else if(idresult == 0 && emailresult == 1) {
    		result = "one_email";
    	}
    	else {
    		result = "both";
    	}
    	return "{\"result\" : \"" + result+ " \" }";
    }    

    /*
    // 회원가입
    @RequestMapping("/join")
    public String join() {
        return "join";
    }
    
    // id 중복여부
    @PostMapping("/ismemberidexist")
    @ResponseBody
    public String isMemberIdExist(@RequestParam("memberId") String memberId) {
        boolean isExist = service.isMemberIdExist(memberId);
        return isExist ? "exist" : "notexist";
    }

    // email 중복 여부
    @PostMapping("/ismemberemailexist")
    @ResponseBody
    public String isMemberEmailExist(@RequestParam("memberEmail") String memberEmail) {
        boolean isExist = service.isMemberEmailExist(memberEmail);
        return isExist ? "exist" : "notexist";
    }

    // 회원가입 / 데이터 저장
    @PostMapping("/savememberinfo")
    public String saveMemberInfo(@ModelAttribute MemberDTO memberDto) {
    	service.saveMemberInfo(memberDto);
        return "login"; // 로그인 페이지로 이동하도록 설정
    }*/
    
    // 아이디 찾기
    @RequestMapping("/findId")
    public String findId() {
        return "findId";
    }
    
    // 비밀번호 찾기
    @RequestMapping("/findPw")
    public String findPw() {
    	return "findPw";
    }


}