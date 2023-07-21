package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

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
		mv.addObject("bpd", bpdto);
		mv.setViewName("brandpromodetail");
		return mv;
	}
	
}
