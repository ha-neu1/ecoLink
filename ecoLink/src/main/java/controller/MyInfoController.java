package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyInfoController {
	@Autowired
	
	
	//유저 정보 조회
	@RequestMapping("/myinfo")
    public String myInfo() {
        return "MyInfo";
    }
	
	//유저 정보 수정
	@RequestMapping("/updatemyinfo")
    public String updateUser() {
        return "MyInfoUpdate";
    }
	
	@GetMapping
	
	
	//유저 삭제
	@RequestMapping("/deleteuser")
    public String deleteUser() {
        return "MyInfoUpdate";
    }
	
	//브랜드 북마크 조회
	
	//좋아요한 글 조회
	
	//내가 쓴 글 조회

}
