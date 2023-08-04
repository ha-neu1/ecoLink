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
import org.springframework.web.bind.annotation.RequestPart;
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
			@RequestPart(name = "mem") MemberDTO mdto, @RequestPart(name = "ent") EnterpriseDTO edto, 
			@RequestPart(name = "img1", required = false) MultipartFile file1, @RequestPart(name = "img2", required = false) MultipartFile file2, @RequestPart(name = "img3", required = false) MultipartFile file3,
			@RequestPart(name = "img4", required = false) MultipartFile file4, @RequestPart(name = "img5", required = false) MultipartFile file5, HttpServletResponse response) throws IllegalStateException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (dto.getMemType().equals("enter")) {
			String savePath = "c:/brand/";
			// MultipartFile entdIntroPicImg o //view file upload o
			// new file savepath -> newFileName
			// eudto.setEntdIntroPic(newFileName)

			// view file upload x -> db entdPic1 o
			// param : entdPic1 db data o
			// eudto.setEntPic1(entdPic1);

			if (edto.getEntdMainPic() != null || edto.getEntdIntroPic() != null || edto.getEntdPic1() != null
					|| edto.getEntdPic2() != null || edto.getEntdPic3() != null) {
				if (!file1.isEmpty()) {
					if (edto.getEntdMainPic() == null) {
						MultipartFile entdMainPicImg = file1;
						String newFileName1 = null;

						String originalName1 = entdMainPicImg.getOriginalFilename();
						String beforeExt1 = originalName1.substring(0, originalName1.indexOf("."));
						String ext1 = originalName1.substring(originalName1.indexOf("."));

						newFileName1 = beforeExt1 + "(" + UUID.randomUUID().toString() + ")" + ext1;
						entdMainPicImg.transferTo(new File(savePath + newFileName1));

						edto.setEntdMainPic("/brand/" + newFileName1);
					} else {
						edto.getEntdMainPic();
					}

				} else if (!file2.isEmpty()) {
					if (edto.getEntdIntroPic() == null) {
						MultipartFile entdIntroPicImg = file2;
						String newFileName2 = null;

						String originalName2 = entdIntroPicImg.getOriginalFilename();
						String beforeExt2 = originalName2.substring(0, originalName2.indexOf("."));
						String ext2 = originalName2.substring(originalName2.indexOf("."));

						newFileName2 = beforeExt2 + "(" + UUID.randomUUID().toString() + ")" + ext2;
						entdIntroPicImg.transferTo(new File(savePath + newFileName2));

						edto.setEntdIntroPic("/brand/" + newFileName2);
					} else {
						edto.getEntdIntroPic();
					}

				} else if (!file3.isEmpty()) {
					if (edto.getEntdPic1() == null) {
						MultipartFile entdPic1Img = file3;
						String newFileName3 = null;

						String originalName3 = entdPic1Img.getOriginalFilename();
						String beforeExt3 = originalName3.substring(0, originalName3.indexOf("."));
						String ext3 = originalName3.substring(originalName3.indexOf("."));

						newFileName3 = beforeExt3 + "(" + UUID.randomUUID().toString() + ")" + ext3;
						entdPic1Img.transferTo(new File(savePath + newFileName3));

						edto.setEntdPic1("/brand/" + newFileName3);
					} else {
						edto.getEntdPic1();
					}

				} else if (!file4.isEmpty()) {
					if (edto.getEntdPic2() == null) {
						MultipartFile entdPic2Img = file4;
						String newFileName4 = null;

						String originalName4 = entdPic2Img.getOriginalFilename();
						String beforeExt4 = originalName4.substring(0, originalName4.indexOf("."));
						String ext4 = originalName4.substring(originalName4.indexOf("."));

						newFileName4 = beforeExt4 + "(" + UUID.randomUUID().toString() + ")" + ext4;
						entdPic2Img.transferTo(new File(savePath + newFileName4));

						edto.setEntdPic2("/brand/" + newFileName4);
					} else {
						edto.getEntdPic2();
					}

				} else if (!file5.isEmpty()) {
					if (edto.getEntdPic3() == null) {
						MultipartFile entdPic3Img = file5;
						String newFileName5 = null;

						String originalName5 = entdPic3Img.getOriginalFilename();
						String beforeExt5 = originalName5.substring(0, originalName5.indexOf("."));
						String ext5 = originalName5.substring(originalName5.indexOf("."));

						newFileName5 = beforeExt5 + "(" + UUID.randomUUID().toString() + ")" + ext5;
						entdPic3Img.transferTo(new File(savePath + newFileName5));

						edto.setEntdPic3("/brand/" + newFileName5);
					} else {
						edto.getEntdPic3();
					}

				}
				service.userUpdate(mdto);
				service.entUpdate(edto);
			}
		} else {
			service.userUpdate(mdto);
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

		
		if (dto.getMemType().equals("enter")) {
			service.deleteEnt(edto);
			service.deleteUser(dto);
		} else {
			service.deleteUser(dto);
		}

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
