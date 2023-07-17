package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import service.MainService;

@Controller
public class MainController {
	@Autowired
	MainService mainService;

	@GetMapping("/main")
	public String Main(Model model) {
		String bannerId = "3e3aedc7-1c65-11ee-950c-d8bbc13a7098"; //
		String bannerPic = mainService.getBannerPicByBannerId(bannerId);
		model.addAttribute("bannerPic", bannerPic);
		int memberCount = mainService.getMemberCount();
        model.addAttribute("memberCount", memberCount);
        int enterCount = mainService.getEnterCount();
        model.addAttribute("enterCount", enterCount);
        int boardCount = mainService.getBoardCount();
        model.addAttribute("boardCount", boardCount);
		return "main";
	}
}