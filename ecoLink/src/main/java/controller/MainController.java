package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.MainService;

@Controller
public class MainController {

	@RequestMapping("/")
	public String mainPage() {
		return "main"; // jsp 파일의 이름
	}

	private final MainService mainService;

	@Autowired
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}

	@GetMapping("/main")
    public String showMainPage(Model model) {
        String bannerId = "3e3aedc7-1c65-11ee-950c-d8bbc13a7098"; // 
        String bannerPic = mainService.getBannerPicByBannerId(bannerId);
        model.addAttribute("bannerPic", bannerPic);
        return "main";
    }
}