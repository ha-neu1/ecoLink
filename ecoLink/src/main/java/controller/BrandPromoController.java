package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BrandPromoController {
	
	@RequestMapping("/brandpromolist")
	public String brandpromolist() {
		return "brandpromolist";
	}
	
}
