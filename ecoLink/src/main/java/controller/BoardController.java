package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import dto.BoardLikeDTO;
import dto.FileDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;

@Controller
public class BoardController {

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/reviewboard")
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
	public String boardCreateForm(Model model, HttpSession session) {
		
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {
		return "boardCreate";
	}

	@PostMapping("/boardCreate")
	public String createBoard(@RequestParam BoardDTO boardDTO) {
		boardService.createBoard(boardDTO);
		return "redirect:/board";
	}

	@GetMapping("/boardRead")
	public String boardRead(@RequestParam(value = "boardId", required = false, defaultValue = "0") int boardId,
			Model model, HttpSession session) {
		if (boardId <= 0) {
            model.addAttribute("error", "해당 게시물을 찾을 수 없습니다.");
        } else {
            BoardDTO boardDTO = boardService.getBoardById(boardId);
            if (boardDTO != null) {
                boardService.increaseViewCount(boardId);
                model.addAttribute("board", boardDTO);

                int countLike = boardService.countLikes(boardId);
                boolean hasLiked = false;

                model.addAttribute("countLike", countLike);
                model.addAttribute("hasLiked", hasLiked);
            } else {
                model.addAttribute("error", "해당 게시물을 찾을 수 없습니다.");
            }
        }
		
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {
		
		return "boardRead";
	}
	
	@PostMapping("/like")
    @ResponseBody
    public ResponseEntity<String> likeBoard(@RequestParam int boardId, @RequestBody BoardLikeDTO likeDTO) {
        likeDTO.setBoardId(boardId);
        if (boardService.hasLiked(likeDTO)) {
            boardService.deleteLike(likeDTO);
        } else {
            boardService.insertLike(likeDTO);
        }
        return ResponseEntity.ok("success");
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
	public String showUpdateForm(@PathVariable int boardId, Model model, HttpSession session) {
		BoardDTO board = boardService.getBoardUpdate(boardId); // 수정된 부분
		model.addAttribute("board", board);
		
		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {
		
		return "boardUpdate";
	}

	@PostMapping("/boardUpdate/{boardId}")
	public String updateBoard(@PathVariable int boardId, @ModelAttribute BoardDTO boardDTO) {
		boardDTO.setBoardId(boardId);
		boardService.updateBoard(boardDTO);
		return "redirect:/boardRead?boardId=" + boardId;
	}

	@GetMapping("/confirmDelete/{boardId}")
	public String showDeleteConfirm(@PathVariable int boardId, Model model) {
		BoardDTO board = boardService.getBoardById(boardId);
		model.addAttribute("board", board);
		return "confirmDelete";
	}

	@PostMapping("/boardDelete/{boardId}")
	public String deleteBoard(@PathVariable int boardId) {
		boardService.deleteBoard(boardId);
		return "redirect:/board";
	}

	@GetMapping("/reviewwriting")
	public String reviewwriting(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {
		return "reviewBoardWrite";
	}

	@PostMapping("/reviewwriting")
	public ModelAndView writeprocess(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			BoardDTO boarddto, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			String boardContents, HttpServletResponse response, HttpSession session) throws IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String savePath = "";
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			savePath = "c:/kdt/upload/";
		} else if (os.contains("linux")) {
			savePath = "/usr/mydir/upload/";
		} else {
			savePath = "c:/kdt/upload/";
		}

		List<MultipartFile> files = boarddto.getFiles();
		List<MultipartFile> draggedFiles = boarddto.getDraggedFiles();

		String loggedInUserId = dto != null ? dto.getMemId() : null;
		if (loggedInUserId == null) {

			ModelAndView mv = new ModelAndView("login"); // Replace "login" with the appropriate login page view name.
			mv.addObject("message", "로그인을 하시기 바랍니다.");
			return mv;
		}

		// Set the memId in the boarddto before inserting into the database
		boarddto.setMemId(loggedInUserId);
		boarddto.setBoardContents(boardContents.replace("\r\n", "<br>"));
		int insertcount = boardService.insertBoard(boarddto);

		List<FileDTO> fileDTOList = new ArrayList<>();

		if (files != null && !files.isEmpty()) {
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					String originalName = file.getOriginalFilename();
					String beforeExt = originalName.substring(0, originalName.indexOf("."));
					String ext = originalName.substring(originalName.indexOf("."));

					String newFilename = beforeExt + "(" + UUID.randomUUID().toString() + ")" + ext;
					File newFile = new File(savePath + newFilename);
					file.transferTo(newFile);

					FileDTO fileDTO = new FileDTO();
					fileDTO.setFileIdx(UUID.randomUUID().toString());
					fileDTO.setFilePath(savePath + newFilename);
					fileDTO.setFileName(originalName);
					fileDTO.setFileType(file.getContentType());

					fileDTO.setBoardId(boarddto.getBoardId());
					fileDTOList.add(fileDTO);
				}
			}
		}
		if (draggedFiles != null && !draggedFiles.isEmpty()) {
			for (MultipartFile file : draggedFiles) {
				if (!file.isEmpty()) {
					String originalName = file.getOriginalFilename();
					String beforeExt = originalName.substring(0, originalName.indexOf("."));
					String ext = originalName.substring(originalName.indexOf("."));

					String newFilename = beforeExt + "(" + UUID.randomUUID().toString() + ")" + ext;
					File newFile = new File(savePath + newFilename);
					file.transferTo(newFile);

					FileDTO fileDTO = new FileDTO();
					fileDTO.setFileIdx(UUID.randomUUID().toString());
					fileDTO.setFilePath(savePath + newFilename);
					fileDTO.setFileName(originalName);
					fileDTO.setFileType(file.getContentType());

					fileDTO.setBoardId(boarddto.getBoardId());
					fileDTOList.add(fileDTO);
				}
			}
		}
		for (FileDTO fileDTO : fileDTOList) {

			int insertfile = boardService.insertFile(fileDTO);

		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		mv.addObject("insertcount", insertcount);
		mv.setViewName("redirect:/reviewboard");
		return mv;
	}
}