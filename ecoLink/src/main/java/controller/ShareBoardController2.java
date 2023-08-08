package controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import dto.MemberDTO;
import service.ShareBoardService2;

@Controller
public class ShareBoardController2 {

	@Autowired
	@Qualifier("shareBoardServiceImpl2")
	ShareBoardService2 service;

	@RequestMapping("/shareboardlist")
	public ModelAndView shareboardlist(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "order", required = false, defaultValue = "latest") String order,
			@RequestParam(value = "search", required = false, defaultValue = "") String search) {
		ModelAndView mv = new ModelAndView();
		int limitindex = (page - 1) * 9;
		int limitcount = 9;
		int totalList = 0;
		HashMap<String, Object> clistmap = new HashMap<String, Object>();
		clistmap.put("limitindex", limitindex);
		clistmap.put("limitcount", limitcount);
		List<BoardDTO> list = null;

		if (search.equals("")) {
			if (order != null) {
				if (order.equals("latest")) {
					list = service.getNormalBPList(clistmap);
					mv.addObject("order", "latest");
				} else if (order.equals("viewcount")) {
					list = service.getviewcountBPList(clistmap);
					mv.addObject("order", "viewcount");
				}
			} else {
				list = service.getNormalBPList(clistmap);
				mv.addObject("order", "latest");
			}
			totalList = service.getBPListCount();
		} else {
			if (order != null) {
				if (order.equals("latest")) {
					clistmap.put("option", "b.boardRegtime");
					clistmap.put("option2", "asc");
					clistmap.put("memNick", search);
					list = service.getoptionBPList(clistmap);
					mv.addObject("order", "latest");
					mv.addObject("search", search);
				} else if (order.equals("viewcount")) {
					clistmap.put("option", "b.boardViewCount");
					clistmap.put("option2", "desc");
					clistmap.put("memNick", search);
					list = service.getoptionBPList(clistmap);
					mv.addObject("order", "viewcount");
					mv.addObject("search", search);
				}
			} else {
				list = service.getNormalBPList(clistmap);
				mv.addObject("order", "latest");
			}
			totalList = service.getoptionBPListCount(search);
		}

		int totalPage = 0;
		if (totalList % 9 == 0) {
			totalPage = totalList / 9;
		} else {
			totalPage = (totalList / 9) + 1;
		}
		int startpage = page / 9 * 9 + 1;
		if (page % 9 == 0) {
			startpage -= 9;
		}
		int endpage = startpage + 9 - 1;
		if (endpage > totalPage) {
			endpage = totalPage;
		}
		mv.addObject("logininfo", dto);
		mv.addObject("currentCpage", page);
		mv.addObject("user", dto);
		mv.addObject("totalPage", totalPage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("list", list);
		mv.setViewName("shareboardlist");
		return mv;
	}

}
