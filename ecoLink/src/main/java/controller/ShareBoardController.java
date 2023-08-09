package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardLikeDTO;
import dto.FileDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ShareBoardService;

@Controller
public class ShareBoardController {

	private final ShareBoardService shareBoardService;

	@Autowired
	public ShareBoardController(ShareBoardService shareBoardService) {
		this.shareBoardService = shareBoardService;
	}

	/*
	 * @GetMapping("/shareboard") public String shareboard(Model model, HttpSession
	 * session) { List<BoardDTO> boardlist = shareBoardService.getShareBoardList();
	 * model.addAttribute("boardlist", boardlist);
	 * 
	 * MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를
	 * 가져와서 MemberDTO로 캐스팅 model.addAttribute("user", user); // Model에 사용자 정보를 추가)
	 * return "shareboard"; }
	 * 
	 * @GetMapping("/boardSearch") public String
	 * boardSearch(@RequestParam("keyword") String keyword, Model model) {
	 * List<BoardDTO> searchResults =
	 * shareBoardService.searchBoardsByKeyword(keyword);
	 * model.addAttribute("searchResults", searchResults); return
	 * "boardSearchResult"; }
	 * 
	 * @GetMapping("/boardSort") public String boardSort(@RequestParam("sortOption")
	 * String sortOption, Model model) { List<BoardDTO> sortedBoardList; if
	 * (sortOption.equals("recent")) { sortedBoardList =
	 * shareBoardService.getSortedBoardListByRecent(); } else if
	 * (sortOption.equals("views")) { sortedBoardList =
	 * shareBoardService.getSortedBoardListByViews(); } else { // 기본 정렬 기준은 최신순으로 설정
	 * sortedBoardList = shareBoardService.getSortedBoardListByRecent(); }
	 * model.addAttribute("sortedBoardData", sortedBoardList); return "boardSorted";
	 * }
	 * 
	 * @GetMapping("/boardCreate") public String boardCreateForm(Model model) {
	 * return "boardCreate"; }
	 * 
	 * @PostMapping("/boardCreate") public String createBoard(@RequestParam BoardDTO
	 * boardDTO) { shareBoardService.createBoard(boardDTO); return
	 * "redirect:/share/board"; }
	 * 
	 * @GetMapping("/boardRead") public String boardRead(@RequestParam(value =
	 * "boardId", required = false, defaultValue = "0") int boardId, Model model,
	 * HttpSession session) { if (boardId <= 0) { model.addAttribute("error",
	 * "해당 게시물을 찾을 수 없습니다."); } else { BoardDTO boardDTO =
	 * shareBoardService.getBoardById(boardId); if (boardDTO != null) {
	 * shareBoardService.increaseViewCount(boardId); // 조회수 증가
	 * model.addAttribute("board", boardDTO); } else { model.addAttribute("error",
	 * "해당 게시물을 찾을 수 없습니다."); } }
	 * 
	 * MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를
	 * 가져와서 MemberDTO로 캐스팅 model.addAttribute("user", user); // Model에 사용자 정보를 추가)
	 * return "boardRead"; }
	 * 
	 * @PostMapping("/increaseViewCount")
	 * 
	 * @ResponseBody public void increaseViewCount(@RequestParam int boardId) {
	 * shareBoardService.increaseViewCount(boardId); }
	 * 
	 * @GetMapping("/getBoardViewCount")
	 * 
	 * @ResponseBody public int getBoardViewCount(@RequestParam int boardId) {
	 * return shareBoardService.getBoardViewCount(boardId); }
	 * 
	 * @GetMapping("/boardUpdate/{boardId}") public String
	 * showUpdateForm(@PathVariable int boardId, Model model, HttpSession session) {
	 * BoardDTO board = shareBoardService.getBoardById(boardId);
	 * model.addAttribute("board", board);
	 * 
	 * MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를
	 * 가져와서 MemberDTO로 캐스팅 model.addAttribute("user", user); // Model에 사용자 정보를 추가)
	 * 
	 * return "boardUpdate"; }
	 * 
	 * @PostMapping("/boardUpdate/{boardId}") public String
	 * updateBoard(@PathVariable int boardId, @ModelAttribute("boardDTO") @Validated
	 * BoardDTO boardDTO, BindingResult result) { if (result.hasErrors()) { // 유효성
	 * 검사 오류가 있는 경우에 대한 처리 return "error"; }
	 * 
	 * // 파일 업로드 처리 List<MultipartFile> files = boardDTO.getFiles(); List<String>
	 * fileNames = new ArrayList<>(); for (MultipartFile file : files) { if
	 * (!file.isEmpty()) { String fileName = file.getOriginalFilename(); String
	 * uploadPath = "/your/upload/directory/path/"; // 파일 업로드 경로 설정 String fullPath
	 * = uploadPath + fileName;
	 * 
	 * try { // 파일 저장 처리 file.transferTo(new File(fullPath));
	 * fileNames.add(fileName); } catch (IOException e) { e.printStackTrace(); // 파일
	 * 업로드 실패 처리 } } }
	 * 
	 * // 게시물 업데이트 처리 BoardDTO updatedBoard =
	 * shareBoardService.getBoardById(boardId); // 게시물 조회
	 * updatedBoard.setBoardTitle(boardDTO.getBoardTitle());
	 * updatedBoard.setBoardContents(boardDTO.getBoardContents());
	 * updatedBoard.setFiles(files); // 업로드된 파일 이름 설정
	 * 
	 * shareBoardService.updateBoard(updatedBoard); // 게시물 업데이트
	 * 
	 * // 게시물 업데이트 후 리다이렉트 return "redirect:/share/boardRead?boardId=" + boardId; }
	 * 
	 * @GetMapping("/confirmDelete/{boardId}") public String
	 * showDeleteConfirm(@PathVariable int boardId, Model model) { BoardDTO board =
	 * shareBoardService.getBoardById(boardId); model.addAttribute("board", board);
	 * return "confirmDelete"; }
	 * 
	 * @PostMapping("/deleteBoard")
	 * 
	 * @ResponseBody public ResponseEntity<String> deleteBoard(@RequestParam Long
	 * boardId) { try { shareBoardService.deleteBoard(boardId); return
	 * ResponseEntity.ok("게시물이 삭제되었습니다."); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("게시물 삭제 오류: " +
	 * e.getMessage()); } }
	 */
//여기부터
	@GetMapping("/sharewriting")
	public String sharewriting(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {
		return "shareBoardWrite";
	}

	@PostMapping("/sharewriting")
	public ModelAndView sharewriting(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
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
		int insertcount = shareBoardService.insertBoard(boarddto);

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

			int insertfile = shareBoardService.insertFile(fileDTO);

		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		mv.addObject("insertcount", insertcount);
		mv.setViewName("redirect:/shareboardlist");
		return mv;
	}
	
	@GetMapping("/shareeditform")
	public String shareeditform(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(name = "boardId") Integer boardId, HttpServletResponse response, HttpSession session,
			Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅

		BoardDTO boarddto = shareBoardService.updateViewcountAndGetDetail(boardId);
		model.addAttribute("detaildto", boarddto);
		List<FileDTO> files = shareBoardService.getFilesByBoardId(boardId);
		List<String> imageUrls = new ArrayList<>();

		for (FileDTO file : files) {
			imageUrls.add(file.getFilePath());

		}
		model.addAttribute("imageUrls", imageUrls);

		model.addAttribute("user", user);
		model.addAttribute("boardId", boardId);// Model에 사용자 정보를 추가) {
		return "shareeditform";
	}

	@PostMapping("/shareeditform")
	public ModelAndView shareeditform(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(name = "boardId") Integer boardId, String boardContents, BoardDTO boarddto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletResponse response,
			HttpSession session) throws IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String savePath = "";
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			savePath = "c:/kdt";
		} else if (os.contains("linux")) {
			savePath = "/usr/mydir";
		} else {
			savePath = "c:/kdt";
		}
		List<FileDTO> deletefiles = shareBoardService.getFilesByBoardId(boardId);
		System.out.println(deletefiles);
		for (FileDTO fileDTO : deletefiles) {
			File file = new File(savePath + fileDTO.getFilePath());
			System.out.println(file);
			if (file.exists()) {
				file.delete();
			}
		}
		shareBoardService.deleteFile(boardId);
		
		savePath = "";
		os = System.getProperty("os.name").toLowerCase();
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
		ModelAndView mv = new ModelAndView();
		BoardDTO alreadyboarddto = shareBoardService.updateViewcountAndGetDetail(boardId);
		mv.addObject("detaildto", alreadyboarddto);
		List<FileDTO> alreadyfiles = shareBoardService.getFilesByBoardId(boardId);
		List<String> imageUrls = new ArrayList<>();

		for (FileDTO file : alreadyfiles) {
			imageUrls.add(file.getFilePath());

		}
		// Set the memId in the boarddto before inserting into the database
		boarddto.setMemId(loggedInUserId);

		boarddto.setBoardContents(boardContents.replace("\r\n", "<br>"));
		int updatecount = shareBoardService.updateBoard(boarddto);

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

					fileDTO.setBoardId(boardId);
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

					fileDTO.setBoardId(boardId);
					fileDTOList.add(fileDTO);
				}
			}
		}
		for (FileDTO fileDTO : fileDTOList) {
			int insertfile = shareBoardService.insertFile(fileDTO);
		}
		
		mv.addObject("insertcount", updatecount);

		
		

		
		// 최신순으로 게시물 목록을 가져오는 로직
		mv.addObject("user", dto);
		mv.addObject("insertcount", updatecount);
		
		mv.setViewName("redirect:/shareboardlist");
		return mv;
	}
	
	@RequestMapping("/sharepostdetail")
	public ModelAndView sharepostdetail(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(name = "boardId") Integer boardId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletResponse response,
			HttpSession session) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		ModelAndView mv = new ModelAndView();

		if (boardId == null) {
			mv.addObject("error", "Invalid boardId"); // 오류 메시지 추가
			mv.setViewName("error-page"); // 오류 페이지로 이동하도록 설정
			return mv;
		}

		BoardDTO boarddto = shareBoardService.updateViewcountAndGetDetail(boardId);
		mv.addObject("detaildto", boarddto);
		List<FileDTO> files = shareBoardService.getFilesByBoardId(boardId);
		List<String> imageUrls = new ArrayList<>();

		for (FileDTO file : files) {
			imageUrls.add(file.getFilePath());

		}
		int totalComment = shareBoardService.getCommentCountForBoard(boardId);

		if (page == 0) {
			page = 1;
		}
		int limitindex = (page - 1) * 5;
		int limitcount = 5;
		HashMap<String, Object> clistmap = new HashMap<String, Object>();
		clistmap.put("boardId", boardId);
		clistmap.put("limitindex", limitindex);
		clistmap.put("limitcount", limitcount);
		List<BoardCommentDTO> clist = shareBoardService.getAllBoardComment(clistmap);

		List<BoardCommentDTO> replyList = shareBoardService.getAllBoardReply(boardId);

		int totalPage = 0;
		if (totalComment % 5 == 0) {
			totalPage = totalComment / 5;
		} else {
			totalPage = (totalComment / 5) + 1;
		}

		int startpage = page / 5 * 5 + 1;
		if (page % 5 == 0) {
			startpage -= 5;
		}
		int endpage = startpage + 5 - 1;
		if (endpage > totalPage) {
			endpage = totalPage;
		}
		if (totalPage == 0) {
			totalPage = 1;
		}
		if (dto != null) {
			boolean hasLiked = shareBoardService.hasUserLikedBoard(dto.getMemId(), boardId);

			mv.addObject("hasLiked", hasLiked);
		} else {
			mv.addObject("hasLiked", false);
		}
		int countLike = shareBoardService.countLike(boardId);

		mv.addObject("replyList", replyList);
		mv.addObject("countLike", countLike);
		mv.addObject("currentCpage", page);
		mv.addObject("totalPage", totalPage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("clist", clist);
		mv.addObject("user", dto);
		mv.addObject("imageUrls", imageUrls);
		mv.addObject("bId", boardId);
		mv.setViewName("sharepostdetail");
		return mv;
	}
	@RequestMapping("/insertShareBoardComment")
	@ResponseBody
	public ResponseEntity<String> insertShareBoardComment(
			@SessionAttribute(name = "logininfo", required = false) MemberDTO dto, String comment, String boardId,
			HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {

		if (dto != null) {
			BoardCommentDTO boarddto = new BoardCommentDTO();
			boarddto.setBcContents(comment.replace("\r\n", "<br>"));
			boarddto.setMemId(dto.getMemId());
			boarddto.setBoardId(Integer.parseInt(boardId));
			int result = shareBoardService.insertBoardComment(boarddto);
			if (result > 0) {
				// Update bcRef value for the newly inserted comment
				int insertedBcId = boarddto.getBcId(); // Assuming the bcId is set after insertion
				int updateResult = shareBoardService.updateBcRef(insertedBcId);
				if (updateResult > 0) {
				} else {
				}
			} else {
			}
			return ResponseEntity.ok().build();
		} else {
			System.out.println("User is not logged in. Redirecting to /login.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in");
		}
	}

	@RequestMapping("/insertShareReplyComment")
	@ResponseBody
	public ResponseEntity<String> insertShareReplyComment(
			@SessionAttribute(name = "logininfo", required = false) MemberDTO dto, String reply, String boardId,
			int bcRef, HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {

		if (dto != null) {
			BoardCommentDTO boarddto = new BoardCommentDTO();
			boarddto.setBcContents(reply.replace("\r\n", "<br>"));
			boarddto.setMemId(dto.getMemId());
			boarddto.setBoardId(Integer.parseInt(boardId));
			boarddto.setBcRef(bcRef);
			int result = shareBoardService.insertReplyComment(boarddto);

			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in");
		}
	}

	@RequestMapping("/insertShareBoardLike")
	@ResponseBody
	public int insertShareBoardLike(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam("boardId") int boardId, HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user);
		if (dto != null) {
			// Check if the user has already liked the board
			boolean hasLiked = shareBoardService.hasUserLikedBoard(dto.getMemId(), boardId);

			if (hasLiked) {
				// If the user has already liked the board, delete the like
				shareBoardService.deleteBoardLike(dto.getMemId(), boardId);
			} else {
				// If the user has not liked the board, insert a new like
				BoardLikeDTO likedto = new BoardLikeDTO();
				likedto.setMemId(dto.getMemId());
				likedto.setBoardId(boardId);
				int result = shareBoardService.insertBoardLike(likedto);
			}

			int updatedLikeCount = shareBoardService.countLike(boardId);

			return updatedLikeCount;
		} else {
			// If the user is not logged in, redirect to the login page
			return 0;
		}

	}

	@RequestMapping("/sharedeleteBoard")
	public String sharedeleteBoard(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto, int boardId,
			String memId, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		String savePath = "";
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			savePath = "c:/kdt";
		} else if (os.contains("linux")) {
			savePath = "/usr/mydir";
		} else {
			savePath = "c:";
		}
		if (dto != null) {
			if (dto.getMemType().equals("admin") || dto.getMemId().equals(memId)) {

				List<FileDTO> files = shareBoardService.getFilesByBoardId(boardId);
				for (FileDTO fileDTO : files) {
					File file = new File(savePath + fileDTO.getFilePath());
					if (file.exists()) {
						file.delete();
					}
				}

				shareBoardService.deleteAllBoard(boardId);
				
				return "redirect:/shareboardlist";
			} else {
				return "/login";
			}
		} else {
			return "/login";
		}
	}

	@RequestMapping("/sharedeleteComment")
	public String sharedeleteComment(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, int bcId, int boardId,
			String memId, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null) {
			if (dto.getMemType().equals("admin") || dto.getMemId().equals(memId)) {
				shareBoardService.deleteComment(bcId);
				return "redirect:/sharepostdetail?boardId=" + boardId + "&page=" + page;
			} else {
				return "/login";
			}
		} else {
			return "/login";
		}
	}

	@RequestMapping("/sharedeleteReply")
	public String sharedeleteReply(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, int bcId, int boardId,
			String memId, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null) {
			if (dto.getMemType().equals("admin") || dto.getMemId().equals(memId)) {
				shareBoardService.deleteReply(bcId);
				return "redirect:/sharepostdetail?boardId=" + boardId + "&page=" + page;
			} else {
				return "/login";
			}
		} else {
			return "/login";
		}
	}
	
}