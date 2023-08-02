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

	// 유저 정보 조회
	@GetMapping("/userInfo")
	public ModelAndView myInfo(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);

		if (dto != null) {
			if (dto.getMemType().equals("enter")) {
				EnterpriseDTO edto = service.getEntUser(dto.getMemId());
				mv.addObject("loginUser", dto);
				mv.addObject("loginEnt", edto);
				mv.setViewName("EntInfo");
			} else {
				mv.addObject("loginUser", dto);
				mv.setViewName("MyInfo");
			}
		} else { // x
			mv.setViewName("redirect:/logout");
		}

		return mv;
	}

	// 유저 정보 수정
	@GetMapping("/updateUserInfo")
	public ModelAndView myInfoupdate(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);

		if(dto != null) {
			if (dto.getMemType().equals("enter")) {
				EnterpriseDTO edto = service.getEntUser(dto.getMemId());
				mv.addObject("loginUser", dto);
				mv.addObject("loginEnt", edto);
				mv.setViewName("EntInfoUpdate");
			} else {
				mv.addObject("loginUser", dto);
				mv.setViewName("MyInfoUpdate");
			}
		}else {
			mv.setViewName("redirect:/logout");
		}
		
		return mv;
	}

	@PostMapping("/updateUserInfo")
	public @ResponseBody String myInfoupdatesql(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response, String memId, String memPw, String memNick, String entPhone,
	String entdMainPic, String entdShort, String entdURL, String entdIntro, String entdIntroPic,
	String entdPic1, String entdPic2, String entdPic3, String entdExplain1, String entdExplain2, String entdExplain3) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		MemberDTO updto = new MemberDTO();
		updto.setMemId(memId);
		updto.setMemPw(memPw);
		updto.setMemNick(memNick);
		
		EnterpriseDTO eudto = new EnterpriseDTO();
		eudto.setEntPhone(entPhone);
		eudto.setEntdMainPic(entdMainPic);
		eudto.setEntdShort(entdShort);
		eudto.setEntdURL(entdURL);
		eudto.setEntdIntro(entdIntroPic);
		eudto.setEntdIntroPic(entdIntroPic);
		eudto.setEntdPic1(entdPic1);
		eudto.setEntdPic2(entdPic2);
		eudto.setEntdPic3(entdPic3);
		eudto.setEntdExplain1(entdExplain1);
		eudto.setEntdExplain2(entdExplain2);
		eudto.setEntdExplain3(entdExplain3);
		
		if (dto.getMemType().equals("enter")) {
			String savePath = "c:/brand/";
			/*
			 * if() {
			 * 
			 * }
			 */
			service.userUpdate(updto);
			service.entUpdate(eudto);
		} else {
			service.userUpdate(updto);
		}
		return "";
	}

	// 유저 삭제
	@RequestMapping("/deleteUser")
	public String deleteUser(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response, EnterpriseDTO edto) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		/*
		 * if (dto.getMemType().equals("enter")) { service.deleteEnt(edto);
		 * service.deleteUser(dto); } else { service.deleteUser(dto); }
		 */

		service.deleteUser(dto);
		
		return "redirect:/logout";
	}

	// 브랜드 북마크 조회
	
	@GetMapping("/myBrandLike")
	public ModelAndView myBrandLike(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		/*
		 * String memId = (String) session.getAttribute("memId"); MemberDTO loginuser =
		 * service.getUser(memId); List<EnterpriseBookmarkDTO> bookmarkList =
		 * likeService.brandBookmark(memId); ArrayList<EnterpriseDTO> brandList = new
		 * ArrayList<EnterpriseDTO>();
		 * 
		 * for (EnterpriseBookmarkDTO bookmarkdto : bookmarkList) { EnterpriseDTO
		 * enterprisedto = likeService.getbrandbyId(bookmarkdto.getEntCrn());
		 * bookmarkList.add(enterprisedto); }
		 * 
		 * for (EnterpriseDTO dto : brandList) { String entdMainPic =
		 * dto.getEntdMainPic(); dto.setEntdMainPic(entdMainPic); }
		 */

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		/* mv.addObject("brandList", brandList); */
		mv.setViewName("MyInfo2");
		return mv;

	}
	 

	// 좋아요한 글 조회
	@RequestMapping("/myBoardLike")
	public String myBoardLike() {
		return "MyInfo3";
	}
	// GetMapping("/myBoardLike")
	// public ModelAndView myBoardLike(HttpSession session){
	//// String memId = (String)session.getAttribute("memId");
	// }

	// 내가 쓴 글 조회
	@RequestMapping("/myBoard")
	public String myBoard() {
		return "MyInfo4";
	}
	// GetMapping("/myBoard")
	// public ModelAndView myBoard(HttpSession session){
	//// String memId = (String)session.getAttribute("memId");
	// MemberDTO loginuser = service.getUser(memId);
	// }

}
