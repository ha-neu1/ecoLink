package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.AdminDTO;
import dto.BannerDTO;
import dto.BoardDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminServiceImpl")
	AdminService service;
	
	@RequestMapping("/admin")
	public ModelAndView adminMain(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) { 
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin") && dto.getMemType().equals("admin")) {
			int noConfirmedEnter = service.getRegEnterConfirm();
			int allUserCount = service.getAllRegUser();
			List<BoardDTO> boardlist = service.getSomeBoardList();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("noConfirmedEnter", noConfirmedEnter);
			mv.addObject("allUserCount", allUserCount);
			mv.addObject("boardlist", boardlist);
			mv.setViewName("adminMain");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
	}
	
	@PostMapping("/admin/login")
	public String adminlogin(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response, HttpServletRequest request, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		MemberDTO dto = service.adminLogin(memberDTO);
		if (dto != null && dto.getMemType().equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("logininfo", dto);
			return "redirect:/admin";
		} else {
			model.addAttribute("loginfail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "adminlogin";
		}
	}
	
	@PostMapping("/admin/logout")
	public String adminlogout(HttpServletResponse response, HttpServletRequest request) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }

	    return "adminlogin";
	}
	
	@RequestMapping("/adminBoardNews")
	public ModelAndView adminBoardNews(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<BoardDTO> boardlist = service.getNewsBoardList();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("boardlist", boardlist);
			mv.addObject("title", "뉴스 게시판 관리 페이지");
			mv.addObject("tabletitle", "뉴스 게시판 최신 글 목록");
			mv.setViewName("adminBoard");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminBoardShare")
	public ModelAndView adminBoardShare(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<BoardDTO> boardlist = service.getShareBoardList();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("boardlist", boardlist);
			mv.addObject("title", "공유 게시판 관리 페이지");
			mv.addObject("tabletitle", "공유 게시판 최신 글 목록");
			mv.setViewName("adminBoard");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminBoardReview")
	public ModelAndView adminBoardReview(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<BoardDTO> boardlist = service.getReviewBoardList();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("boardlist", boardlist);
			mv.addObject("title", "리뷰 게시판 관리 페이지");
			mv.addObject("tabletitle", "리뷰 게시판 최신 글 목록");
			mv.setViewName("adminBoard");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminBoardTips")
	public ModelAndView adminBoardTips(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<BoardDTO> boardlist = service.getTipsBoardList();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("boardlist", boardlist);
			mv.addObject("title", "팁 게시판 관리 페이지");
			mv.addObject("tabletitle", "팁 게시판 최신 글 목록");
			mv.setViewName("adminBoard");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminEnterReg")
	public ModelAndView adminEnterReg(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<AdminDTO> boardlist = service.getUnConfirmedEnterList();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("boardlist", boardlist);
			mv.addObject("title", "기업 회원 관리 - 가입 승인 요청");
			mv.addObject("tabletitle", "회원가입 요청 기업 리스트");
			mv.setViewName("adminEnter");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminEnterAll")
	public ModelAndView adminEnterAll(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<AdminDTO> boardlist = service.getConfirmedEnterList();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("boardlist", boardlist);
			mv.addObject("title", "기업 회원 관리 - 가입 기업 리스트");
			mv.addObject("tabletitle", "기업 리스트");
			mv.setViewName("adminEnter");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminBanner")
	public ModelAndView adminBanner(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<BannerDTO> bannerlist = service.getAllBanner();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("bannerlist", bannerlist);
			mv.addObject("tabletitle", "등록된 배너 리스트");
			mv.addObject("banneramount", bannerlist.size());
			mv.setViewName("adminBanner");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminBannerinput")
	public ModelAndView adminBannerinput(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response, BannerDTO bdto) throws IllegalStateException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			String savePath = "";
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				savePath = "c:/banner/";
			} else if (os.contains("linux")) {
				savePath = "/usr/mydir/banner/";
			} else {
				savePath = "c:/banner/";
			}
			if (bdto.getFile() != null) {
				if (!bdto.getFile().isEmpty()) {
					MultipartFile file1 = bdto.getFile();
					String originalname1 = file1.getOriginalFilename();
					file1.transferTo(new File(savePath + originalname1));
					
					bdto.setBannerPic("/banner/" + originalname1);
					bdto.setMemId(dto.getMemId());
					int result = service.addBanner(bdto);
					List<BannerDTO> bannerlist = service.getAllBanner();
					mv.addObject("adminUserId", dto.getMemId());
					mv.addObject("bannerlist", bannerlist);
					mv.addObject("tabletitle", "등록된 배너 리스트");
					mv.addObject("banneramount", bannerlist.size());
					mv.setViewName("adminBanner");
					return mv;
				} else {
					List<BannerDTO> bannerlist = service.getAllBanner();
					mv.addObject("adminUserId", dto.getMemId());
					mv.addObject("bannerlist", bannerlist);
					mv.addObject("tabletitle", "등록된 배너 리스트");
					mv.addObject("banneramount", bannerlist.size());
					mv.setViewName("adminBanner");
					return mv;
				}
				
			} else {
				List<BannerDTO> bannerlist = service.getAllBanner();
				mv.addObject("adminUserId", "배너 등록에 실패하였습니다. 다시 시도해 주십시오.");
				mv.addObject("adminUserId", dto.getMemId());
				mv.addObject("bannerlist", bannerlist);
				mv.addObject("tabletitle", "등록된 배너 리스트");
				mv.addObject("banneramount", bannerlist.size());
				mv.setViewName("adminBanner");
				return mv;
			}
			
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminMember")
	public ModelAndView adminMember(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<MemberDTO> memberlist = service.getAllNormalMember();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("memberlist", memberlist);
			mv.addObject("title", "회원 관리 - 가입 회원 리스트");
			mv.addObject("tabletitle", "일반 회원 리스트");
			mv.setViewName("adminMember");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminAccount")
	public ModelAndView adminAccount(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto != null && dto.getMemType().equals("admin")) {
			List<MemberDTO> memberlist = service.getAllAdminMember();
			mv.addObject("adminUserId", dto.getMemId());
			mv.addObject("memberlist", memberlist);
			mv.addObject("title", "관리자");
			mv.addObject("tabletitle", "관리자 리스트");
			mv.setViewName("adminMember");
			return mv;
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}
		
	}
	
	@RequestMapping("/adminAddaccount")
	public String adminAddaccount(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, Model model, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null && dto.getMemType().equals("admin")) {
			model.addAttribute("adminUserId", dto.getMemId());
			return "adminAddaccount";
		} else {
			return "adminlogin";
		}
		
	}
	
	@RequestMapping("/adminadd")
	public ModelAndView adminadd(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto2, String inputId, String inputName, String inputEmail, String inputPassword, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		ModelAndView mv = new ModelAndView();
		if (dto2 != null && dto2.getMemType().equals("admin")) {
			MemberDTO dto = new MemberDTO();
			dto.setMemId(inputId);
			dto.setMemNick(inputName);
			dto.setMemName(inputName);
			dto.setMemEmail(inputEmail);
			dto.setMemPw(inputPassword);
			int valid = service.getSpecificAdminAccount(dto);
			if (valid == 0) {
				int result = 0;
				try {
					result = service.addAdminAccount(dto);
				} catch (Exception e) {
					
				}
				if (result == 1) {
					List<MemberDTO> memberlist = service.getAllAdminMember();
					mv.addObject("adminUserId", dto2.getMemId());
					mv.addObject("memberlist", memberlist);
					mv.addObject("title", "관리자");
					mv.addObject("tabletitle", "관리자 리스트");
					mv.setViewName("adminMember");
					return mv;
				} else {
					mv.addObject("adminUserId", dto2.getMemId());
					mv.addObject("result", "계정 생성에 실패했습니다. (아이디중복여부, 이메일중복여부 혹은 올바른 양식인지 확인 바랍니다.)");
					mv.setViewName("adminAddaccount");
					return mv;
				}
			} else {
				mv.addObject("adminUserId", dto2.getMemId());
				mv.addObject("result", "계정 생성에 실패했습니다. (아이디중복여부, 이메일중복여부 혹은 올바른 양식인지 확인 바랍니다.)");
				mv.setViewName("adminAddaccount");
				return mv;
			}
		} else {
			mv.setViewName("adminlogin");
			return mv;
		}

	}
	
	@RequestMapping("/deleteAccount")
	public String deleteAccount(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, String memId, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null && dto.getMemType().equals("admin")) {
			int result = service.deleteAdminAccount(memId);
			return "redirect:/adminAccount";
		} else {
			return "adminlogin";
		}
	}
	
	@RequestMapping("/deleteBanner")
	public String deleteBanner(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, String bannerId, String bannerPic, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null && dto.getMemType().equals("admin")) {
			String savePath = "";
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				savePath = "c:";
			} else if (os.contains("linux")) {
				savePath = "/usr/mydir";
			} else {
				savePath = "c:";
			}
			File file = new File(savePath + bannerPic);
			if (file.exists()) {
				file.delete();
			}
			int result = service.deleteBanner(bannerId);
			return "redirect:/adminBanner";
		} else {
			return "adminlogin";
		}
	}
	
	@RequestMapping("/controlEnterReg")
	public String controlEnterReg(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, String entCrn, String allow, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null && dto.getMemType().equals("admin")) {
			if (allow.equals("allow")) {
				int result = service.enterRegAllow(entCrn);
				return "redirect:/adminEnterReg";
			} else {
				return "redirect:/adminEnterReg";
			}
			
		} else {
			return "adminlogin";
		}
	}
}