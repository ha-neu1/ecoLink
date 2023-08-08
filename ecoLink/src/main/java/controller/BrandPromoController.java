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

import dto.BrandCommentDTO;
import dto.BrandPromoDTO;
import dto.BrandPromoListDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BrandPromoService;

@Controller
public class BrandPromoController {

	@Autowired
	@Qualifier("brandPromoServiceImpl")
	BrandPromoService service;

	@RequestMapping("/brandpromolist")
	public ModelAndView brandpromolist(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
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
		List<BrandPromoListDTO> list = null;

		if (search.equals("")) {
			if (order != null) {
				if (order.equals("latest")) {
					list = service.getNormalBPList(clistmap);
					mv.addObject("order", "latest");
				} else if (order.equals("rate")) {
					list = service.getrateBPList(clistmap);
					mv.addObject("order", "rate");
				}
			} else {
				list = service.getNormalBPList(clistmap);
				mv.addObject("order", "latest");
			}
			totalList = service.getBPListCount();
		} else {
			if (order != null) {
				if (order.equals("latest")) {
					clistmap.put("option", "m.memRegtime");
					clistmap.put("memNick", search);
					list = service.getoptionBPList(clistmap);
					mv.addObject("order", "latest");
					mv.addObject("search", search);
				} else if (order.equals("rate")) {
					clistmap.put("option", "avgRate");
					clistmap.put("memNick", search);
					list = service.getoptionBPList(clistmap);
					mv.addObject("order", "rate");
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
		mv.addObject("currentCpage", page);
		mv.addObject("user", dto);
		mv.addObject("totalPage", totalPage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("list", list);
		mv.setViewName("brandpromolist");
		return mv;
	}

	@RequestMapping("/brandpromodetail")
	public ModelAndView brandpromodetail(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletRequest request,
			String focus, String entCrn) {
		ModelAndView mv = new ModelAndView();
		BrandPromoDTO bpdto = service.getBrandPromoDetail(entCrn);
		int totalComment = service.getCommentCount(entCrn);
		double rate = service.getCommentAvgRate(entCrn);
		if (page == 0) {
			page = 1;
		}
		int limitindex = (page - 1) * 5;
		int limitcount = 5;
		HashMap<String, Object> clistmap = new HashMap<String, Object>();
		clistmap.put("entCrn", entCrn);
		clistmap.put("limitindex", limitindex);
		clistmap.put("limitcount", limitcount);
		List<BrandCommentDTO> clist = service.getAllBrandComment(clistmap);
		List<BrandCommentDTO> clistall = service.getAllBrandComments(clistmap);
		int bookmarked = 0;
		if (dto != null) {
			bookmarked = service.getBrandPromoBookmark(dto.getMemId(), entCrn);
			List<String> nameList = clistall.stream().map(BrandCommentDTO::getMemId).collect(Collectors.toList());
			if (nameList.contains(dto.getMemId())) {
				mv.addObject("commentinserted", "yes");
			}
		}
		if (bookmarked == 0) {
			mv.addObject("bookmarked", 0);
		} else {
			mv.addObject("bookmarked", 1);
		}

		if (rate < 2) {
			mv.addObject("rateColor", "#FF0000");
			mv.addObject("rateTextColor", "#FFF");
		} else if (rate < 3) {
			mv.addObject("rateColor", "#FFA500");
			mv.addObject("rateTextColor", "#FFF");
		} else if (rate < 4) {
			mv.addObject("rateColor", "#FFFF00");
			mv.addObject("rateTextColor", "#000000");
		} else if (rate < 5) {
			mv.addObject("rateColor", "#00D084");
			mv.addObject("rateTextColor", "#FFF");
		} else {
			mv.addObject("rateColor", "#00D084");
			mv.addObject("rateTextColor", "#FFF");
		}
		int totalPage = 0;
		if (totalComment % 5 == 0) {
			totalPage = totalComment / 5;
		} else {
			totalPage = (totalComment / 5) + 1;
		}
		int startpage = page / 5 * 5 + 1;
		if (page % 5 == 0) {
			startpage -= 5;
		}
		int endpage = startpage + 5 - 1;
		if (endpage > totalPage) {
			endpage = totalPage;
		}
		HttpSession session = request.getSession();
		if (focus != null) {
			if (focus.equals("true")) {
				session.setAttribute("focus", "true");
			} else {
				session.setAttribute("focus", "false");
			}
		} else {
			session.setAttribute("focus", "false");
		}

		mv.addObject("currentCpage", page);
		mv.addObject("totalPage", totalPage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("clist", clist);
		mv.addObject("user", dto);
		mv.addObject("rate", rate);
		mv.addObject("bpd", bpdto);
		mv.setViewName("brandpromodetail");
		return mv;
	}

	@RequestMapping("/brandpromodetail/bookmark")
	public String bookmark(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto, String entCrn,
			String bookmarked) {
		if (dto != null && entCrn != null) {
			int result = 0;
			if (bookmarked.equals("0")) {
				result = service.insertBrandPromoBookmark(dto.getMemId(), entCrn);
			} else if (bookmarked.equals("1")) {
				result = service.deleteBrandPromoBookmark(dto.getMemId(), entCrn);
			}
		}
		return "redirect:/brandpromodetail?entCrn=" + entCrn;
	}

	@RequestMapping("/insertBrandComment")
	public String insertBrandComment(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto, String star,
			String comment, String entCrn) {
		if (dto != null) {
			BrandCommentDTO bcdto = new BrandCommentDTO();
			bcdto.setBrcRate(Integer.parseInt(star));
			bcdto.setBrcContents(comment.replace("\r\n", "<br>"));
			bcdto.setEntCrn(entCrn);
			bcdto.setMemId(dto.getMemId());
			int result = service.insertBrandComment(bcdto);
			return "redirect:/brandpromodetail?entCrn=" + entCrn;
		} else {
			return "/login";
		}

	}

	@RequestMapping("/deleteBrandComment")
	public String deleteBrandComment(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			String memId, String entCrn, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null) {
			if (dto.getMemType().equals("admin") || dto.getMemId().equals(memId)) {
				int result = service.deleteBrandComment(entCrn, memId);
				return "redirect:/brandpromodetail?entCrn=" + entCrn;
			} else {
				return "/login";
			}
		} else {
			return "/login";
		}
	}

	@RequestMapping("/updateBrandComment")
	public String updateBrandComment(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			String memId, String brcRate, String brcContents, String entCrn, int currentCpage) {
		if (dto != null) {
			if (dto.getMemType().equals("admin") || dto.getMemId().equals(memId)) {
				BrandCommentDTO bcdto = new BrandCommentDTO();
				bcdto.setBrcRate(Integer.parseInt(brcRate));
				bcdto.setBrcContents(brcContents.replace("\r\n", "<br>"));
				bcdto.setEntCrn(entCrn);
				bcdto.setMemId(memId);
				int result = service.updateBrandComment(bcdto);
				return "redirect:/brandpromodetail?entCrn=" + entCrn + "&page=" + currentCpage + "&focus=true";
			} else {
				return "/login";
			}
		} else {
			return "/login";
		}
	}
}
