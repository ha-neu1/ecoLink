package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.AdminDTO;
import dto.BannerDTO;
import dto.BoardDTO;
import dto.MemberDTO;
import service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminServiceImpl")
	AdminService service;
	
	@RequestMapping("/admin")
	public ModelAndView adminMain() {
		ModelAndView mv = new ModelAndView();
		int noConfirmedEnter = service.getRegEnterConfirm();
		int allUserCount = service.getAllRegUser();
		List<BoardDTO> boardlist = service.getSomeBoardList();
		mv.addObject("noConfirmedEnter", noConfirmedEnter);
		mv.addObject("allUserCount", allUserCount);
		mv.addObject("boardlist", boardlist);
		mv.setViewName("adminMain");
		return mv;
	}
	
	@RequestMapping("/adminBoardNews")
	public ModelAndView adminBoardNews() {
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> boardlist = service.getNewsBoardList();
		mv.addObject("boardlist", boardlist);
		mv.addObject("title", "뉴스 게시판 관리 페이지");
		mv.addObject("tabletitle", "뉴스 게시판 최신 글 목록");
		mv.setViewName("adminBoard");
		return mv;
	}
	
	@RequestMapping("/adminBoardShare")
	public ModelAndView adminBoardShare() {
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> boardlist = service.getShareBoardList();
		mv.addObject("boardlist", boardlist);
		mv.addObject("title", "공유 게시판 관리 페이지");
		mv.addObject("tabletitle", "공유 게시판 최신 글 목록");
		mv.setViewName("adminBoard");
		return mv;
	}
	
	@RequestMapping("/adminBoardReview")
	public ModelAndView adminBoardReview() {
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> boardlist = service.getReviewBoardList();
		mv.addObject("boardlist", boardlist);
		mv.addObject("title", "리뷰 게시판 관리 페이지");
		mv.addObject("tabletitle", "리뷰 게시판 최신 글 목록");
		mv.setViewName("adminBoard");
		return mv;
	}
	
	@RequestMapping("/adminBoardTips")
	public ModelAndView adminBoardTips() {
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> boardlist = service.getTipsBoardList();
		mv.addObject("boardlist", boardlist);
		mv.addObject("title", "팁 게시판 관리 페이지");
		mv.addObject("tabletitle", "팁 게시판 최신 글 목록");
		mv.setViewName("adminBoard");
		return mv;
	}
	
	@RequestMapping("/adminEnterReg")
	public ModelAndView adminEnterReg() {
		ModelAndView mv = new ModelAndView();
		List<AdminDTO> boardlist = service.getUnConfirmedEnterList();
		mv.addObject("boardlist", boardlist);
		mv.addObject("title", "기업 회원 관리 - 가입 승인 요청");
		mv.addObject("tabletitle", "회원가입 요청 기업 리스트");
		mv.setViewName("adminEnter");
		return mv;
	}
	
	@RequestMapping("/adminEnterAll")
	public ModelAndView adminEnterAll() {
		ModelAndView mv = new ModelAndView();
		List<AdminDTO> boardlist = service.getConfirmedEnterList();
		mv.addObject("boardlist", boardlist);
		mv.addObject("title", "기업 회원 관리 - 가입 기업 리스트");
		mv.addObject("tabletitle", "기업 리스트");
		mv.setViewName("adminEnter");
		return mv;
	}
	
	@RequestMapping("/adminBanner")
	public ModelAndView adminBanner() {
		ModelAndView mv = new ModelAndView();
		List<BannerDTO> bannerlist = service.getAllBanner();
		mv.addObject("bannerlist", bannerlist);
		mv.addObject("tabletitle", "등록된 배너 리스트");
		mv.addObject("banneramount", bannerlist.size());
		mv.setViewName("adminBanner");
		return mv;
	}
	
	@RequestMapping("/adminMember")
	public ModelAndView adminMember() {
		ModelAndView mv = new ModelAndView();
		List<MemberDTO> memberlist = service.getAllNormalMember();
		mv.addObject("memberlist", memberlist);
		mv.addObject("title", "회원 관리 - 가입 회원 리스트");
		mv.addObject("tabletitle", "일반 회원 리스트");
		mv.setViewName("adminMember");
		return mv;
	}
}
