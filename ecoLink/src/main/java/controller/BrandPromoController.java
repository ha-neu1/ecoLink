package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import dto.BrandCommentDTO;
import dto.BrandPromoDTO;
import dto.MemberDTO;
import service.BrandPromoService;



@Controller
public class BrandPromoController {
	
	@Autowired
	@Qualifier("brandPromoServiceImpl")
	BrandPromoService service;
	
	@RequestMapping("/brandpromolist")
	public String brandpromolist() {
		return "brandpromolist";
	}
	
	@RequestMapping("/brandpromodetail")
	public ModelAndView brandpromodetail(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, String entCrn) {
		ModelAndView mv = new ModelAndView();
		BrandPromoDTO bpdto = service.getBrandPromoDetail(entCrn);
		int bookmarked = 0;
		if (dto != null) {
			bookmarked = service.getBrandPromoBookmark(dto.getMemId(), entCrn);
		}
		if (bookmarked == 0) {
			mv.addObject("bookmarked", 0);
		} else {
			mv.addObject("bookmarked", 1);
		}
		double rate = service.getCommentAvgRate(entCrn);
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
		mv.addObject("user", dto);
		mv.addObject("rate", rate);
		mv.addObject("bpd", bpdto);
		mv.setViewName("brandpromodetail");
		return mv;
	}
	
	@RequestMapping("/brandpromodetail/bookmark")
	public String bookmark(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, String entCrn, String bookmarked) {
		if (dto != null && entCrn != null) {
			int result = 0;
			if (bookmarked.equals("0")) {
				result = service.insertBrandPromoBookmark(dto.getMemId(), entCrn);
			} 
			else if (bookmarked.equals("1")) {
				result = service.deleteBrandPromoBookmark(dto.getMemId(), entCrn);
			}
		}
		return "redirect:/brandpromodetail?entCrn=" + entCrn;
	}
	
	@RequestMapping("/insertBrandComment")
	public String insertBrandComment(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, String star, String comment, String entCrn) {
		if (dto != null) {
			BrandCommentDTO bcdto = new BrandCommentDTO();
			bcdto.setBrcRate(Integer.parseInt(star));
			bcdto.setBrcContents(comment);
			bcdto.setEntCrn(entCrn);
			bcdto.setMemId(dto.getMemId());
			int result = service.insertBrandComment(bcdto);
			return "redirect:/brandpromodetail?entCrn=" + entCrn;
		} else {
			return "/login";
		}
		
	}
}
