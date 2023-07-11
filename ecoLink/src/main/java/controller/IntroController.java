package controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@RequestMapping("/infopostdetail")
	public String infopostdetail() {
		return "infopostdetail";
	}
	
	@RequestMapping("/infoboardlist")
	public ModelAndView infoboardlist(@RequestParam(value = "page", required = false, defaultValue = "1")int page,
			@RequestParam(value = "selectValue", required = false, defaultValue = "recent")String selectValue){
		int totalBoard = service.getTotalBoard();
		
		int limitcount = 5;
		int limitindex = (page-1)*limitcount;
		int limit [] = new int[2];
		limit[0] = limitindex;
		limit[1] = limitcount;
		
		List<BoardDTO> boardList = service.boardListRecent(limit);
		
		if(selectValue.equals("recent")) {
			boardList = service.boardListRecent(limit);
		}
		else if(selectValue.equals("view")) {
			boardList = service.boardListView(limit);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("totalBoard", totalBoard);
		//mv.addObject("boardListRecent", listRecent);
		//mv.addObject("boardListView", listView);
		mv.addObject("boardList", boardList);
		
		mv.setViewName("infoarticle");
		return mv;
	}
	
	/*
	 * @RequestMapping("/infoboardsearch") public ModelAndView
	 * infoboardsearch(@RequestParam(value="item", required = false, defaultValue =
	 * "all")
	 */
	
	@RequestMapping("/infowriting")
	public String infowriting() {
		return "infowritingform";
	}
}
