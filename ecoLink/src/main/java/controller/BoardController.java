package controller;

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

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String board(Model model) {
        model.addAttribute("boardList", boardService.getBoardList());
        return "board";
    }

    @GetMapping("/boardCreate")
    public String boardCreateForm() {
        return "boardCreateForm";
    }

    @PostMapping("/boardCreate")
    public String createBoard(BoardDTO boardDTO) {
        boardService.createBoard(boardDTO);
        return "redirect:/board";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam("boardId") int boardId, Model model) {
        BoardDTO boardDTO = boardService.getBoardById(boardId);
        model.addAttribute("board", boardDTO);
        return "boardDetail";
    }

    @PostMapping("/boardUpdate")
    public String updateBoard(BoardDTO boardDTO) {
        boardService.updateBoard(boardDTO);
        return "redirect:/board";
    }

    @GetMapping("/boardDelete")
    public String deleteBoard(@RequestParam("boardId") int boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/board";
    }
}
