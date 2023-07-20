package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.EnterpriseDTO;
import dto.EnterpriseBookmarkDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import service.MyInfoLikeService;
import service.MyInfoService;

@Controller
public class MyInfoLikeController {
	@Autowired
	MyInfoService service;
	@Autowired
	MyInfoLikeService likeService;
	
	//브랜드 북마크 조회
	/*
	 * @GetMapping("/myBrandLike") public ModelAndView myBrandLike(HttpSession
	 * session){ String memId = (String)session.getAttribute("memId"); MemberDTO
	 * loginuser = service.getUser(memId); List<EnterpriseBookmarkDTO> bookmarkList
	 * = likeService.brandBookmark(memId); ArrayList<EnterpriseDTO> brandList = new
	 * ArrayList<EnterpriseDTO>();
	 * 
	 * for(EnterpriseBookmarkDTO bookmarkdto : bookmarkList) { EnterpriseDTO
	 * enterprisedto = likeService.getbrandbyId(bookmarkdto.getEntCrn());
	 * bookmarkList.add(enterprisedto); }
	 * 
	 * for(EnterpriseDTO dto : brandList) { String entdMainPic =
	 * dto.getEntdMainPic(); dto.setEntdMainPic(entdMainPic); }
	 * 
	 * ModelAndView mv = new ModelAndView(); mv.addObject("brandList", brandList);
	 * mv.setViewName("MyInfo2"); return mv;
	 * 
	 * }
	 */
	
	//좋아요한 글 조회
	@RequestMapping("/myBoardLike")
    public String myBoardLike() {
        return "MyInfo3";
    }
	//GetMapping("/myBoardLike")
	//public ModelAndView myBoardLike(HttpSession session){
	////String memId = (String)session.getAttribute("memId");
	//}
	
	//내가 쓴 글 조회
	@RequestMapping("/myBoard")
    public String myBoard() {
        return "MyInfo4";
    }
	//GetMapping("/myBoard")
	//public ModelAndView myBoard(HttpSession session){
	////String memId = (String)session.getAttribute("memId");
	//MemberDTO loginuser = service.getUser(memId);
	//}

}
