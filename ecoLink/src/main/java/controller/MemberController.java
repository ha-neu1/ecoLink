package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import service.MemberService;

@Controller
public class MemberController {

    // 로그인
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

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

//    @PostMapping("/join")
//    public String join(MemberDTO member) {
//        service.addMember(member);
//        return "redirect:/login";
//    }
    
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
