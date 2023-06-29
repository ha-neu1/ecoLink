package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroController {
	
	@RequestMapping("/introboard")
	public String introboard() {
		return "info_board/introboard";

	}
	@RequestMapping("/infoarticle")
	public String infoarticle() {
		return "info_board/infoarticle";

	}
}
