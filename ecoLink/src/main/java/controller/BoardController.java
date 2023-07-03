package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

	@RequestMapping("/board")
	public String board() {
		return "board";
	}
	
	@GetMapping("/boardCreate")
	public String boardCreate() {
		return "boardCreate";
	}
	@GetMapping("/boardRead")
	public String boardRead() {
		return "boardRead";
	}
	@GetMapping("/boardUpdate")
	public String boardUpdate() {
		return "boardUpdate";
	}

}
