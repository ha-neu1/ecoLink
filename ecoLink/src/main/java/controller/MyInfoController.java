package controller;

import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import dto.BrandCommentDTO;
import dto.EnterpriseDTO;
import dto.EnterpriseBookmarkDTO;
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
				MemberDTO user = service.getUser(dto.getMemId());
				dto.setMemPw(user.getMemPw());
				dto.setMemNick(user.getMemNick());
				
				mv.addObject("loginUser", dto);
				mv.addObject("loginEnt", edto);
				mv.setViewName("EntInfo");
			} else {
				MemberDTO user = service.getUser(dto.getMemId());
				dto.setMemPw(user.getMemPw());
				dto.setMemNick(user.getMemNick());
				
				mv.addObject("loginUser", dto);
				mv.setViewName("MyInfo");
			}
		} else { // x
			mv.setViewName("login");
		}

		return mv;
	}

	// 일반회원 정보 수정
	@GetMapping("/updateUserInfo")
	public ModelAndView myInfoupdate(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);

		if (dto != null) {
				mv.addObject("loginUser", dto);
				mv.setViewName("MyInfoUpdate");
		} else {
			mv.setViewName("login");
		}

		return mv;
	}
	
	@PostMapping("/updateUserInfo")
	public @ResponseBody String myInfoupdatesql(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response, String memId, String memPw, String memNick) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		MemberDTO updto = new MemberDTO();
		updto.setMemId(memId);
		updto.setMemPw(memPw);
		updto.setMemNick(memNick);
		
		service.userUpdate(updto);
		
		return "redirect:/userInfo";
	}
	
	// 기업회원 정보 수정
	@GetMapping("/updateEntInfo")
	public ModelAndView entInfoupdate(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);

		if (dto != null) {
				EnterpriseDTO edto = service.getEntUser(dto.getMemId());
				mv.addObject("loginUser", dto);
				mv.addObject("loginEnt", edto);
				mv.setViewName("EntInfoUpdate");
		} else {
			mv.setViewName("login");
		}

		return mv;
	}
	
	@PostMapping("/updateEntInfo")
	public @ResponseBody String entInfoupdatesql(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestPart(name = "mem") MemberDTO mdto, @RequestPart(name = "ent") EnterpriseDTO edto,
			@RequestPart(name = "img1", required = false) MultipartFile file1,
			@RequestPart(name = "img2", required = false) MultipartFile file2,
			@RequestPart(name = "img3", required = false) MultipartFile file3,
			@RequestPart(name = "img4", required = false) MultipartFile file4,
			@RequestPart(name = "img5", required = false) MultipartFile file5, HttpServletResponse response)
			throws IllegalStateException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		String savePath = "";
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			savePath = "c:/brand/";
		} else if (os.contains("linux")) {
			savePath = "/usr/mydir/brand/";
		} else {
			savePath = "c:/brand/";
		}
			// MultipartFile entdIntroPicImg o //view file upload o
			// new file savepath -> newFileName
			// eudto.setEntdIntroPic(newFileName)

			// view file upload x -> db entdPic1 o
			// param : entdPic1 db data o
			// eudto.setEntPic1(entdPic1);

			if (edto.getEntdMainPic() != null || edto.getEntdIntroPic() != null || edto.getEntdPic1() != null
					|| edto.getEntdPic2() != null || edto.getEntdPic3() != null) {
				if (file1 != null) {
					MultipartFile entdMainPicImg = file1;
					String newFileName1 = null;

					String originalName1 = entdMainPicImg.getOriginalFilename();
					String beforeExt1 = originalName1.substring(0, originalName1.indexOf("."));
					String ext1 = originalName1.substring(originalName1.indexOf("."));

					newFileName1 = beforeExt1 + "(" + UUID.randomUUID().toString() + ")" + ext1;
					entdMainPicImg.transferTo(new File(savePath + newFileName1));

					edto.setEntdMainPic("/brand/" + newFileName1);
				}

				if (file2 != null) {
					MultipartFile entdIntroPicImg = file2;
					String newFileName2 = null;

					String originalName2 = entdIntroPicImg.getOriginalFilename();
					String beforeExt2 = originalName2.substring(0, originalName2.indexOf("."));
					String ext2 = originalName2.substring(originalName2.indexOf("."));

					newFileName2 = beforeExt2 + "(" + UUID.randomUUID().toString() + ")" + ext2;
					entdIntroPicImg.transferTo(new File(savePath + newFileName2));

					edto.setEntdIntroPic("/brand/" + newFileName2);
				}

				if (file3 != null) {
					MultipartFile entdPic1Img = file3;
					String newFileName3 = null;

					String originalName3 = entdPic1Img.getOriginalFilename();
					String beforeExt3 = originalName3.substring(0, originalName3.indexOf("."));
					String ext3 = originalName3.substring(originalName3.indexOf("."));

					newFileName3 = beforeExt3 + "(" + UUID.randomUUID().toString() + ")" + ext3;
					entdPic1Img.transferTo(new File(savePath + newFileName3));

					edto.setEntdPic1("/brand/" + newFileName3);
				}

				if (file4 != null) {
					MultipartFile entdPic2Img = file4;
					String newFileName4 = null;

					String originalName4 = entdPic2Img.getOriginalFilename();
					String beforeExt4 = originalName4.substring(0, originalName4.indexOf("."));
					String ext4 = originalName4.substring(originalName4.indexOf("."));

					newFileName4 = beforeExt4 + "(" + UUID.randomUUID().toString() + ")" + ext4;
					entdPic2Img.transferTo(new File(savePath + newFileName4));

					edto.setEntdPic2("/brand/" + newFileName4);
				}

				if (file5 != null) {
					MultipartFile entdPic3Img = file5;
					String newFileName5 = null;

					String originalName5 = entdPic3Img.getOriginalFilename();
					String beforeExt5 = originalName5.substring(0, originalName5.indexOf("."));
					String ext5 = originalName5.substring(originalName5.indexOf("."));

					newFileName5 = beforeExt5 + "(" + UUID.randomUUID().toString() + ")" + ext5;
					entdPic3Img.transferTo(new File(savePath + newFileName5));

					edto.setEntdPic3("/brand/" + newFileName5);
				}
				
				EnterpriseDTO en = service.getEntUser(dto.getMemId());
				
				
				if (dto.getMemPw() == mdto.getMemPw() && dto.getMemNick() == mdto.getMemNick()
						&& edto.getEntPhone() == en.getEntPhone() && edto.getEntdMainPic() == en.getEntdMainPic()
						&& edto.getEntdShort() == en.getEntdShort() && edto.getEntdURL() == en.getEntdURL()
						&& edto.getEntdIntro() == en.getEntdIntro() && edto.getEntdIntroPic() == en.getEntdIntroPic()
						&& edto.getEntdPic1() == en.getEntdPic1() && edto.getEntdPic2() == en.getEntdPic2()
						&& edto.getEntdPic3() == en.getEntdPic3() && edto.getEntdExplain1() == en.getEntdExplain1()
						&& edto.getEntdExplain2() == en.getEntdExplain2()
						&& edto.getEntdExplain3() == en.getEntdExplain3()) {
					edto.setEntdConfirm(true);
				} else {
					edto.setEntdConfirm(false);
				}
				
				String modifiedIntro = convertToHtmlFormat(edto.getEntdIntro());
				edto.setEntdIntro(modifiedIntro);
		        
		    	String modifiedShort = convertToHtmlFormat(edto.getEntdShort());
		    	edto.setEntdShort(modifiedShort);
		        
		        String modifiedExplain1 = convertToHtmlFormat(edto.getEntdExplain1());
		        edto.setEntdExplain1(modifiedExplain1);

		        String modifiedExplain2 = convertToHtmlFormat(edto.getEntdExplain2());
		        edto.setEntdExplain2(modifiedExplain2);

		        String modifiedExplain3 = convertToHtmlFormat(edto.getEntdExplain3());
		        edto.setEntdExplain3(modifiedExplain3);

				service.userUpdate(mdto);
				service.entUpdate(edto);
			}

		return "redirect:/userInfo";
	}
	
	public String convertToHtmlFormat(String text) {
        String htmlText = text.replace("\r\n", "<br>")
                              .replace("\n", "<br>")
                              .replace("\r", "<br>");
        
        return htmlText;
    }

	// 유저 삭제
	@RequestMapping("/deleteUser")
	public String deleteUser(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (dto.getMemType().equals("enter")) {
			EnterpriseDTO edto = service.getEntUser(dto.getMemId());
			
			service.deleteBC(dto.getMemId());
			service.deleteEBM(dto.getMemId());
			service.deleteEnt(edto);
			service.deleteUser(dto);
		} else {
			service.deleteLike(dto.getMemId());
			service.deleteUser(dto);
		}

		return "redirect:/logout";
	}

	// 브랜드 북마크 조회
	@GetMapping("/myBrandLike")
	public ModelAndView myBrandLike(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();

		if (dto != null) {
			int limitindex = (page - 1) * 9;
			int limitcount = 9;
			int totalBookmark = 0;

			HashMap<String, Object> clistmap = new HashMap<String, Object>();
			clistmap.put("limitindex", limitindex);
			clistmap.put("limitcount", limitcount);
			clistmap.put("memId", dto.getMemId()); // memId를 clistmap에 추가

			List<EnterpriseDTO> Bookmark = service.getBrandBookmark(clistmap);
			totalBookmark = service.getBookmarkCount(dto.getMemId());

			int totalPage = 0;
			if (totalBookmark % 9 == 0) {
				totalPage = totalBookmark / 9;
			} else {
				totalPage = (totalBookmark / 9) + 1;
			}
			int startpage = page / 9 * 9 + 1;
			if (page % 9 == 0) {
				startpage -= 9;
			}
			int endpage = startpage + 9 - 1;
			if (endpage > totalPage) {
				endpage = totalPage;
			}

			mv.addObject("user", dto);
			mv.addObject("Bookmark", Bookmark);
			mv.addObject("currentpPage", page);
			mv.addObject("totalPage", totalPage);
			mv.addObject("startpage", startpage);
			mv.addObject("endpage", endpage);
			mv.setViewName("MyInfo2");
		} else {
			mv.setViewName("redirect:/logout");
		}
		return mv;
	}

	// 좋아요한 글 조회
	@RequestMapping("/myBoardLike")
	public ModelAndView myBoardLike(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();

		if (dto != null) {
			int limitindex = (page - 1) * 10;
			int limitcount = 10;
			int totalBoardlike = 0;

			HashMap<String, Object> clistmap = new HashMap<String, Object>();
			clistmap.put("limitindex", limitindex);
			clistmap.put("limitcount", limitcount);
			clistmap.put("memId", dto.getMemId()); // memId를 clistmap에 추가

			List<BoardDTO> Boardlike = service.getBoardLike(clistmap);
			totalBoardlike = service.getBoardLikeCount(dto.getMemId());

			int totalPage = 0;
			if (totalBoardlike % 10 == 0) {
				totalPage = totalBoardlike / 10;
			} else {
				totalPage = (totalBoardlike / 10) + 1;
			}
			int startpage = page / 10 * 10 + 1;
			if (page % 10 == 0) {
				startpage -= 10;
			}
			int endpage = startpage + 10 - 1;
			if (endpage > totalPage) {
				endpage = totalPage;
			}

			mv.addObject("user", dto);
			mv.addObject("Boardlike", Boardlike);
			mv.addObject("currentpPage", page);
			mv.addObject("totalPage", totalPage);
			mv.addObject("startpage", startpage);
			mv.addObject("endpage", endpage);
			mv.setViewName("MyInfo3");
		} else {
			mv.setViewName("redirect:/logout");
		}
		return mv;
	}


	// 내가 쓴 글 조회
	@RequestMapping("/myBoard")
	public ModelAndView myBoard(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		ModelAndView mv = new ModelAndView();

		if (dto != null) {
			int limitindex = (page - 1) * 10;
			int limitcount = 10;
			int totalMyBoard = 0;

			HashMap<String, Object> clistmap = new HashMap<String, Object>();
			clistmap.put("limitindex", limitindex);
			clistmap.put("limitcount", limitcount);
			clistmap.put("memId", dto.getMemId()); // memId를 clistmap에 추가

			List<BoardDTO> MyBoard = service.getMyBoard(clistmap);
			totalMyBoard = service.getMyBoardCount(dto.getMemId());

			int totalPage = 0;
			if (totalMyBoard % 10 == 0) {
				totalPage = totalMyBoard / 10;
			} else {
				totalPage = (totalMyBoard / 10) + 1;
			}
			int startpage = page / 10 * 10 + 1;
			if (page % 10 == 0) {
				startpage -= 10;
			}
			int endpage = startpage + 10 - 1;
			if (endpage > totalPage) {
				endpage = totalPage;
			}

			mv.addObject("user", dto);
			mv.addObject("MyBoard", MyBoard);
			mv.addObject("currentpPage", page);
			mv.addObject("totalPage", totalPage);
			mv.addObject("startpage", startpage);
			mv.addObject("endpage", endpage);
			mv.setViewName("MyInfo4");
		} else {
			mv.setViewName("redirect:/logout");
		}
		return mv;
	}
}
