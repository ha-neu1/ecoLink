package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import service.InfoBoardService;

@Controller
public class IntroController {
	@Autowired
	@Qualifier("infoBoardServiceImpl")
	InfoBoardService service;
	
	@RequestMapping("/introboard")
	public String introboard() {
		return "introboard";
	}

	@RequestMapping("/infoboardlist")
	public ModelAndView infoboardlist(@RequestParam(value = "page", required = false, defaultValue = "1")int page) {
		int totalBoard = service.getTotalBoard();
		
		int limitcount = 5;
		int limitindex = (page-1)*limitcount;
		int limit [] = new int[2];
		limit[0] = limitindex;
		limit[1] = limitcount;
		
		List<BoardDTO> list = service.boardList(limit);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("totalBoard", totalBoard);
		mv.addObject("boardList", list);
		
		mv.setViewName("infoarticle");
		return mv;
	}
	@RequestMapping("/infowriting")
	public String infowriting() {
		return "infowritingform";
	}
}
