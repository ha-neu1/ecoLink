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
import service.MyInfoLikeService;

@Controller
public class MyInfoLikeController {
	@Autowired
	MyInfoLikeService service;
	
	//브랜드 북마크 조회
	@RequestMapping("/myBrandLike")
    public String myBrandLike() {
        return "MyInfo2";
    }
	//GetMapping("/myBrandLike")
	//public ModelAndView myBrandLike(HttpSession session){
	//String memId = (String)session.getAttribute("memId");
	//
	//}
	
	//좋아요한 글 조회
	@RequestMapping("/myBoardLike")
    public String myBoardLike() {
        return "MyInfo3";
    }
	//GetMapping("/myBoardLike")
	
	
	//내가 쓴 글 조회
	@RequestMapping("/myBoard")
    public String myBoard() {
        return "MyInfo4";
    }
	//GetMapping("/myBoard")

}
