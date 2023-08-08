package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import dto.BannerDTO;
import dto.MainDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MainService;

@Controller
public class MainController {
	@Autowired
	MainService mainService;

	@GetMapping("/main")
	public String Main(HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String os = System.getProperty("os.name").toLowerCase();

        String savePath = "";
        if (os.contains("win")) {
            savePath = "c:/kdt";
        } else if (os.contains("linux")) {
            savePath = "/usr/mydir";
        } else {
            savePath = "c:/kdt";
        }
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가

		List<BannerDTO> banners = mainService.getAllBanners();
		model.addAttribute("banners", banners);

		List<MainDTO> brandlist = mainService.getBrandList();
		model.addAttribute("brandlist", brandlist);

		List<MainDTO> boardlist = mainService.getShareBoardList(savePath);
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