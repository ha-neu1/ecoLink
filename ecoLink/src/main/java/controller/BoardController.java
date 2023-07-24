package controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dto.MemberDTO;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@RequestMapping("/board")
	public String board(HttpSession session, Model model) {
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가
		return "board";
	}

	@GetMapping("/boardCreate")
	public String boardCreate(HttpSession session, Model model) {
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가
		return "boardCreate";
	}

	@GetMapping("/boardRead")
	public String boardRead(HttpSession session, Model model) {
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가
		return "boardRead";
	}

	@GetMapping("/boardUpdate")
	public String boardUpdate(HttpSession session, Model model) {
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가
		return "boardUpdate";
	}

	@PostMapping("/createBoard")
	@ResponseBody
	public Map<String, Object> createBoard(@RequestParam("memId") String memId,
			@RequestParam("boardTitle") String boardTitle, @RequestParam("boardImage") MultipartFile boardImage,
			@RequestParam("boardContents") String boardContents) {

		Map<String, Object> response = new HashMap<>();
		try {
			response.put("success", true);
			response.put("message", "게시물이 성공적으로 작성되었습니다.");
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "게시물 작성에 실패하였습니다.");
		}

		return response;
	}

}