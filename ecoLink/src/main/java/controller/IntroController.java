package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroController {
	
	@RequestMapping("/introboard")
	public String introboard() {
		return "info_board/introboard";
	}
}
