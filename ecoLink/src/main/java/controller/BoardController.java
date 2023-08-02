package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dto.BoardDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/board")
    public String board(HttpSession session, Model model) {
        MemberDTO user = (MemberDTO) session.getAttribute("logininfo");
        model.addAttribute("user", user);
        // 게시물 목록 조회
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "board";
    }

    @GetMapping("/boardCreate")
    public String boardCreate(HttpSession session, Model model) {
        MemberDTO user = (MemberDTO) session.getAttribute("logininfo");
        model.addAttribute("user", user);
        return "boardCreate";
    }

    @PostMapping("/createBoard")
    @ResponseBody
    public Map<String, Object> createBoard(@RequestParam("boardTitle") String boardTitle,
            @RequestParam("boardImage") MultipartFile boardImage,
            @RequestParam("boardContents") String boardContents) {

        Map<String, Object> response = new HashMap<>();
        try {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardTitle(boardTitle);
            boardDTO.setBoardContents(boardContents);
            // 게시물 작성
            boolean result = boardService.createBoard(boardDTO);
            if (result) {
                response.put("success", true);
                response.put("message", "게시물이 성공적으로 작성되었습니다.");
            } else {
                response.put("success", false);
                response.put("message", "게시물 작성에 실패하였습니다.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시물 작성에 실패하였습니다.");
        }

        return response;
    }

    @GetMapping("/boardRead")
    public String boardRead(HttpSession session, Model model) {
        MemberDTO user = (MemberDTO) session.getAttribute("logininfo");
        model.addAttribute("user", user);
        return "boardRead";
    }

    @GetMapping("/boardUpdate")
    public String boardUpdate(HttpSession session, Model model) {
        MemberDTO user = (MemberDTO) session.getAttribute("logininfo");
        model.addAttribute("user", user);
        return "boardUpdate";
    }

    @PostMapping("/updateBoard")
    @ResponseBody
    public Map<String, Object> updateBoard(@RequestParam("boardId") int boardId,
            @RequestParam("boardTitle") String boardTitle, @RequestParam("boardContents") String boardContents) {

        Map<String, Object> response = new HashMap<>();
        try {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardId(boardId);
            boardDTO.setBoardTitle(boardTitle);
            boardDTO.setBoardContents(boardContents);
            // 게시물 수정
            boolean result = boardService.updateBoard(boardDTO);
            if (result) {
                response.put("success", true);
                response.put("message", "게시물이 성공적으로 수정되었습니다.");
            } else {
                response.put("success", false);
                response.put("message", "게시물 수정에 실패하였습니다.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시물 수정에 실패하였습니다.");
        }

        return response;
    }

    @PostMapping("/deleteBoard")
    @ResponseBody
    public Map<String, Object> deleteBoard(@RequestParam("boardId") int boardId) {

        Map<String, Object> response = new HashMap<>();
        try {
            // 게시물 삭제
            boolean result = boardService.deleteBoard(boardId);
            if (result) {
                response.put("success", true);
                response.put("message", "게시물이 성공적으로 삭제되었습니다.");
            } else {
                response.put("success", false);
                response.put("message", "게시물 삭제에 실패하였습니다.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시물 삭제에 실패하였습니다.");
        }

        return response;
    }
}
