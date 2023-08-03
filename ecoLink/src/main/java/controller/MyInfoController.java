package controller;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
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

		if (dto != null) {
			if (dto.getMemType().equals("enter")) {
				EnterpriseDTO edto = service.getEntUser(dto.getMemId());
				mv.addObject("loginUser", dto);
				mv.addObject("loginEnt", edto);
				mv.setViewName("EntInfoUpdate");
			} else {
				mv.addObject("loginUser", dto);
				mv.setViewName("MyInfoUpdate");
			}
		} else {
			mv.setViewName("redirect:/logout");
		}

		return mv;
	}

	@PostMapping("/updateUserInfo")
	public @ResponseBody String myInfoupdatesql(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response, String memId, String memPw, String memNick, String entPhone,
			String entdMainPic, String entdShort, String entdURL, String entdIntro, String entdIntroPic,
			String entdPic1, String entdPic2, String entdPic3, String entdExplain1, String entdExplain2,
			String entdExplain3) throws IllegalStateException, IOException {
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
			// MultipartFile entdIntroPicImg o //view file upload o
			// new file savepath -> newFileName
			// eudto.setEntdIntroPic(newFileName)

			// view file upload x -> db entdPic1 o
			// param : entdPic1 db data o
			// eudto.setEntPic1(entdPic1);

			if (eudto.getEntdMainPicImg() != null || eudto.getEntdIntroPicImg() != null
					|| eudto.getEntdPic1Img() != null || eudto.getEntdPic2Img() != null
					|| eudto.getEntdPic3Img() != null) {
				if (!eudto.getEntdMainPicImg().isEmpty()) {
					if (eudto.getEntdMainPic() == null) {
						MultipartFile entdMainPicImg = eudto.getEntdMainPicImg();
						String newFileName1 = null;

						String originalName1 = entdMainPicImg.getOriginalFilename();
						String beforeExt1 = originalName1.substring(0, originalName1.indexOf("."));
						String ext1 = originalName1.substring(originalName1.indexOf("."));

						newFileName1 = beforeExt1 + "(" + UUID.randomUUID().toString() + ")" + ext1;
						entdMainPicImg.transferTo(new File(savePath + newFileName1));

						eudto.setEntdMainPic(newFileName1);
					} else {
						eudto.getEntdMainPic();
					}

				} else if (!eudto.getEntdIntroPicImg().isEmpty()) {
					if (eudto.getEntdIntroPic() == null) {
						MultipartFile entdIntroPicImg = eudto.getEntdIntroPicImg();
						String newFileName2 = null;

						String originalName2 = entdIntroPicImg.getOriginalFilename();
						String beforeExt2 = originalName2.substring(0, originalName2.indexOf("."));
						String ext2 = originalName2.substring(originalName2.indexOf("."));

						newFileName2 = beforeExt2 + "(" + UUID.randomUUID().toString() + ")" + ext2;
						entdIntroPicImg.transferTo(new File(savePath + newFileName2));

						eudto.setEntdIntroPic(newFileName2);
					} else {
						eudto.getEntdIntroPic();
					}

				} else if (!eudto.getEntdPic1Img().isEmpty()) {
					if (eudto.getEntdPic1() == null) {
						MultipartFile entdPic1Img = eudto.getEntdPic1Img();
						String newFileName3 = null;

						String originalName3 = entdPic1Img.getOriginalFilename();
						String beforeExt3 = originalName3.substring(0, originalName3.indexOf("."));
						String ext3 = originalName3.substring(originalName3.indexOf("."));

						newFileName3 = beforeExt3 + "(" + UUID.randomUUID().toString() + ")" + ext3;
						entdPic1Img.transferTo(new File(savePath + newFileName3));

						eudto.setEntdPic1(newFileName3);
					} else {
						eudto.getEntdPic1();
					}

				} else if (!eudto.getEntdPic2Img().isEmpty()) {
					if (eudto.getEntdPic2() == null) {
						MultipartFile entdPic2Img = eudto.getEntdPic2Img();
						String newFileName4 = null;

						String originalName4 = entdPic2Img.getOriginalFilename();
						String beforeExt4 = originalName4.substring(0, originalName4.indexOf("."));
						String ext4 = originalName4.substring(originalName4.indexOf("."));

						newFileName4 = beforeExt4 + "(" + UUID.randomUUID().toString() + ")" + ext4;
						entdPic2Img.transferTo(new File(savePath + newFileName4));

						eudto.setEntdPic2(newFileName4);
					} else {
						eudto.getEntdPic2();
					}

				} else if (!eudto.getEntdPic3Img().isEmpty()) {
					if (eudto.getEntdPic3() == null) {
						MultipartFile entdPic3Img = eudto.getEntdPic3Img();
						String newFileName5 = null;

						String originalName5 = entdPic3Img.getOriginalFilename();
						String beforeExt5 = originalName5.substring(0, originalName5.indexOf("."));
						String ext5 = originalName5.substring(originalName5.indexOf("."));

						newFileName5 = beforeExt5 + "(" + UUID.randomUUID().toString() + ")" + ext5;
						entdPic3Img.transferTo(new File(savePath + newFileName5));

						eudto.setEntdPic3(newFileName5);
					} else {
						eudto.getEntdPic3();
					}

				}
				service.userUpdate(updto);
				service.entUpdate(eudto);
			}
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

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		
		if(dto != null) {
			List<EnterpriseDTO> Bookmark =service.getBrandBookmark(dto.getMemId());
			mv.addObject("Bookmark", Bookmark);
			mv.setViewName("MyInfo2");
		}else {
			mv.setViewName("redirect:/logout");
		}
		
		return mv;
	}

	// 좋아요한 글 조회
	@RequestMapping("/myBoardLike")
	public ModelAndView myBoardLike(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		
		if(dto != null) {
			List<BoardDTO> Boardlike =service.getBoardLike(dto.getMemId());
			mv.addObject("Boardlike", Boardlike);
			mv.setViewName("MyInfo3");
		}else {
			mv.setViewName("redirect:/logout");
		}
		
		return mv;
	}

	// 내가 쓴 글 조회
	@RequestMapping("/myBoard")
	public ModelAndView myBoard(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		
		if(dto != null) {
			List<BoardDTO> MyBoard =service.getMyBoard(dto.getMemId());
			mv.addObject("MyBoard", MyBoard);
			mv.setViewName("MyInfo4");
		}else {
			mv.setViewName("redirect:/logout");
		}
		
		return mv;
	}

}
