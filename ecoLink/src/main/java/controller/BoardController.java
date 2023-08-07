package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.BoardService;
import dto.BoardDTO;

@Controller
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/example")
    public String example(@RequestParam int boardId, @RequestParam String memId, @RequestParam String boardTitle) {
        System.out.println("boardId: " + boardId);
        System.out.println("memId: " + memId);
        System.out.println("boardTitle: " + boardTitle);
        return "example";
    }

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/board")
	public String board(Model model) {
		List<BoardDTO> boardlist = boardService.getShareBoardList();
		model.addAttribute("boardlist", boardlist);
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
		return "boardCreate";
	}

	@PostMapping("/boardCreate")
	public String createBoard(@RequestParam BoardDTO boardDTO) {
		boardService.createBoard(boardDTO);
		return "redirect:/board";
	}

	@GetMapping("/boardRead")
	public String boardRead(@RequestParam(value = "boardId", required = false, defaultValue = "0") int boardId, Model model) {
	    if (boardId <= 0) {
	    	model.addAttribute("error", "해당 게시물을 찾을 수 없습니다.");
	    } else {
	        BoardDTO boardDTO = boardService.getBoardById(boardId);
	        model.addAttribute("board", boardDTO);
	    }
	    return "boardRead";
	}

	@PostMapping("/boardUpdate")
	public String updateBoard(@RequestParam BoardDTO boardDTO) {
		boardService.updateBoard(boardDTO);
		return "redirect:/board";
	}

	@GetMapping("/boardDelete")
	public String deleteBoard(@RequestParam("boardId") int boardId) {
		boardService.deleteBoard(boardId);
		return "redirect:/board";
	}
}