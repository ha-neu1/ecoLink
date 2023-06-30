package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.AdminDTO;
import dto.BoardDTO;
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
		mv.addObject("title", "기업 회원가입 관리 페이지");
		mv.addObject("tabletitle", "회원가입 요청 기업 리스트");
		mv.setViewName("adminEnter");
		return mv;
	}
	
	@RequestMapping("/adminEnterAll")
	public ModelAndView adminEnterAll() {
		ModelAndView mv = new ModelAndView();
		List<AdminDTO> boardlist = service.getConfirmedEnterList();
		mv.addObject("boardlist", boardlist);
		mv.addObject("title", "가입 기업 리스트");
		mv.addObject("tabletitle", "기업 리스트");
		mv.setViewName("adminEnter");
		return mv;
	}
}
