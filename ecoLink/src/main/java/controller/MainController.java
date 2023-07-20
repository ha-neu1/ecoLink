package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dto.BannerDTO;
import dto.BoardDTO;
import service.MainService;

@Controller
public class MainController {
	@Autowired
	MainService mainService;

	@GetMapping("/main")
	public String Main(Model model) {
		List<BannerDTO> banners = mainService.getAllBanners();
        model.addAttribute("banners", banners);
        List<BoardDTO> boardlist = mainService.getShareBoardList();
        model.addAttribute("boardlist", boardlist);
		int memberCount = mainService.getMemberCount();
        model.addAttribute("memberCount", memberCount);
        int enterCount = mainService.getEnterCount();
        model.addAttribute("enterCount", enterCount);
        int boardCount = mainService.getBoardCount();
        model.addAttribute("boardCount", boardCount);
		return "main";
	}
}