package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.BoardService;
import dto.BoardDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	private final BoardService boardService;
	private final HttpSession session;

	@Autowired
	public BoardController(BoardService boardService, HttpSession session) {
		this.boardService = boardService;
		this.session = session;
	}

	@GetMapping("/board")
	public String board(Model model) {
		List<BoardDTO> boardlist = boardService.getShareBoardList();
		model.addAttribute("boardlist", boardlist);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo");
		model.addAttribute("user", user);

		return "board";
	}

	@GetMapping("/boardSearch")
	public String boardSearch(@RequestParam("keyword") String keyword, Model model) {
		List<BoardDTO> searchResults = boardService.searchBoardsByKeyword(keyword);
		model.addAttribute("searchResults", searchResults);
		return "boardSearchResult";
	}

	@GetMapping("/boardSort")
	public String boardSort(@RequestParam("sortOption") String sortOption, Model model) {
		List<BoardDTO> sortedBoardList;
		if (sortOption.equals("recent")) {
			sortedBoardList = boardService.getSortedBoardListByRecent();
		} else if (sortOption.equals("views")) {
			sortedBoardList = boardService.getSortedBoardListByViews();
		} else {
			// 기본 정렬 기준은 최신순으로 설정
			sortedBoardList = boardService.getSortedBoardListByRecent();
		}
		model.addAttribute("sortedBoardData", sortedBoardList);
		return "boardSorted";
	}

	@GetMapping("/boardCreate")
	public String boardCreateForm(Model model) {

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo");
		model.addAttribute("user", user);
		
		return "boardCreate";
	}

	@PostMapping("/boardCreate")
	public String createBoard(@ModelAttribute BoardDTO boardDTO) {
		boolean isCreated = boardService.createBoard(boardDTO);

		if (isCreated) {
			int createdBoardId = boardService.getLastCreatedBoardId();
			return "redirect:/boardRead?boardId=" + createdBoardId;
		} else {
			return "error-page";
		}
	}

	@GetMapping("/boardRead")
	public String boardRead(@RequestParam(value = "boardId", required = false, defaultValue = "0") int boardId,
			Model model) {
		if (boardId <= 0) {
			model.addAttribute("error", "해당 게시물을 찾을 수 없습니다.");
		} else {
			BoardDTO boardDTO = boardService.getBoardById(boardId);
			if (boardDTO != null) {
				boardService.increaseViewCount(boardId); // 조회수 증가
				model.addAttribute("board", boardDTO);
			} else {
				model.addAttribute("error", "해당 게시물을 찾을 수 없습니다.");
			}
		}
		
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo");
        model.addAttribute("user", user);
		
		return "boardRead";
	}

	@PostMapping("/increaseViewCount")
	@ResponseBody
	public void increaseViewCount(@RequestParam int boardId) {
		boardService.increaseViewCount(boardId);
	}

	@GetMapping("/getBoardViewCount")
	@ResponseBody
	public int getBoardViewCount(@RequestParam int boardId) {
		return boardService.getBoardViewCount(boardId);
	}

	@GetMapping("/boardUpdate/{boardId}")
	public String showUpdateForm(@PathVariable int boardId, Model model) {
		BoardDTO board = boardService.getBoardById(boardId);
		model.addAttribute("board", board);
		
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo");
        model.addAttribute("user", user);
		
		return "boardUpdate";
	}

	@PostMapping("/boardUpdate/{boardId}")
	public String updateBoard(@PathVariable int boardId, @ModelAttribute BoardDTO boardDTO) {
		boardDTO.setBoardId(boardId);
		boardService.updateBoard(boardDTO);
		return "redirect:/boardUpdate/" + boardId;
	}

	@DeleteMapping("/boardDelete/{boardId}")
	public ResponseEntity<String> deleteBoard(@PathVariable int boardId) {
		boardService.deleteBoard(boardId);
		return ResponseEntity.ok("Board deleted successfully");
	}
}