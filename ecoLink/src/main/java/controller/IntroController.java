package controller;

import java.util.HashMap;
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

	@RequestMapping("/infopostdetail")
	public String infopostdetail() {
		return "infopostdetail";
	}

	@RequestMapping("/infoboardlist")
	public ModelAndView infoboardlist(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "selectValue", required = false, defaultValue = "recent") String selectValue) {
		int totalBoard = service.getTotalBoard();

		int limitcount = 5;
		int limitindex = (page - 1) * limitcount;
		int limit[] = new int[2];
		limit[0] = limitindex;
		limit[1] = limitcount;

		List<BoardDTO> boardList = service.boardListRecent(limit);

		if (selectValue.equals("recent")) {
			boardList = service.boardListRecent(limit);
		} else if (selectValue.equals("view")) {
			boardList = service.boardListView(limit);
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("totalBoard", totalBoard);
		// mv.addObject("boardListRecent", listRecent);
		// mv.addObject("boardListView", listView);
		mv.addObject("boardList", boardList);

		mv.setViewName("infoarticle");
		return mv;
	}

	@RequestMapping("/infoboardsearch")
	public ModelAndView infoboardsearch(
			@RequestParam(value = "item", required = false, defaultValue = "seartch_all") String item,
			@RequestParam(value = "word", required = false, defaultValue = "") String word,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page)
	{
		int limitcount = 5;
		int limitindex = (page - 1) * limitcount;
		
			
		
		HashMap map = new HashMap();
		if (item.equals("seartch_all")) {
			item = null;
		}
		map.put("colname", item);
		map.put("colvalue", "%" + word + "%");
		map.put("limitindex", limitindex);
		map.put("limitcount", limitcount);
		List<BoardDTO> searchlist = service.searchList(map);
		int searchcount = service.getSearchBoard(map);
		
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardList", searchlist);
		mv.addObject("totalBoard", searchcount);
		
		mv.setViewName("infoboardsearch");
		return mv;
	}

	@RequestMapping("/infowriting")
	public String infowriting() {
		return "infowritingform";
	}
	@RequestMapping("/info")
	public String info() {
		return "infotest";
	}
}
