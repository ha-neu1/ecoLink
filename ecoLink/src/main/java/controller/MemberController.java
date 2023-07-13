package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.MemberDTO;
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
	
	@PostMapping("/login")
    public String memberlogin(String memId, String memPw, HttpSession session) {
        MemberDTO dto = service.oneMember(memId); 
        if(dto != null) {
            //DB pw== 입력pw
            if( (dto.getMemPw()).equals(memPw)) { 
                 session.setAttribute("sessionid", memId);
                    return "/main";
            }
            else {
                 session.setAttribute("sessionid","비밀번호가 다릅니다.");
                    return "/login";
            }
        }//if-end
        else {//아이디가 DB에 없을때.
             session.setAttribute("sessionid", "가입되지 않은 아이디입니다.");
            return "/login";
        }
	}//로그인

    // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("sessionid") != null) { // 로그인한 세션 정보가 있으면
            session.removeAttribute("sessionid"); // 세션정보 없애라 = 로그아웃해라
        }
        // return "/main";
        return "redirect:/";
    }
    
    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String memberjoin(@ModelAttribute("member") MemberDTO member) {
        // 일반회원일 경우 memType을 "일반회원"으로 설정
        if (member.getMemType().equals("normal")) {
            member.setMemType("일반회원");
        }
        // 기업회원일 경우 memType을 "기업회원"으로 설정
        else if (member.getMemType().equals("enter")) {
            member.setMemType("기업회원");
        }
        service.addMember(member);
        return "login";
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
    }

    
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
    */
}