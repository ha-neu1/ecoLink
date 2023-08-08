package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardLikeDTO;
import dto.FileDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ReviewBoardService;

@Controller
public class ReviewBoardController {

	@Autowired
	@Qualifier("reviewBoardServiceImpl")
	ReviewBoardService service;


	
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
		int insertcount = service.insertBoard(boarddto);

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

			int insertfile = service.insertFile(fileDTO);

		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("insertcount", insertcount);

		// 최신순으로 게시물 목록을 가져오는 로직
		mv.addObject("user", dto);
		mv.addObject("insertcount", insertcount);

		mv.setViewName("redirect:/main");
		return mv;
	}

	@GetMapping("/revieweditform")
	public String revieweditform(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(name = "boardId") Integer boardId, HttpServletResponse response, HttpSession session,
			Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅

		BoardDTO boarddto = service.updateViewcountAndGetDetail(boardId);
		model.addAttribute("detaildto", boarddto);
		List<FileDTO> files = service.getFilesByBoardId(boardId);
		List<String> imageUrls = new ArrayList<>();

		for (FileDTO file : files) {
			imageUrls.add(file.getFilePath());

		}
		model.addAttribute("imageUrls", imageUrls);

		model.addAttribute("user", user);
		model.addAttribute("boardId", boardId);// Model에 사용자 정보를 추가) {
		return "revieweditform";
	}

	@PostMapping("/revieweditform")
	public ModelAndView revieweditform(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
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
		List<FileDTO> deletefiles = service.getFilesByBoardId(boardId);
		System.out.println(deletefiles);
		for (FileDTO fileDTO : deletefiles) {
			File file = new File(savePath + fileDTO.getFilePath());
			System.out.println(file);
			if (file.exists()) {
				file.delete();
			}
		}
		service.deleteFile(boardId);
		
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
		BoardDTO alreadyboarddto = service.updateViewcountAndGetDetail(boardId);
		mv.addObject("detaildto", alreadyboarddto);
		List<FileDTO> alreadyfiles = service.getFilesByBoardId(boardId);
		List<String> imageUrls = new ArrayList<>();

		for (FileDTO file : alreadyfiles) {
			imageUrls.add(file.getFilePath());

		}
		// Set the memId in the boarddto before inserting into the database
		boarddto.setMemId(loggedInUserId);

		boarddto.setBoardContents(boardContents.replace("\r\n", "<br>"));
		int updatecount = service.updateBoard(boarddto);

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
			int insertfile = service.insertFile(fileDTO);
		}
		
		mv.addObject("insertcount", updatecount);

		

	
		// 최신순으로 게시물 목록을 가져오는 로직
		mv.addObject("user", dto);
		mv.addObject("insertcount", updatecount);
	
		mv.setViewName("redirect:/main");
		return mv;
	}

	@RequestMapping("/reviewpostdetail")
	public ModelAndView reviewpostdetail(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
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

		BoardDTO boarddto = service.updateViewcountAndGetDetail(boardId);
		mv.addObject("detaildto", boarddto);
		List<FileDTO> files = service.getFilesByBoardId(boardId);
		List<String> imageUrls = new ArrayList<>();

		for (FileDTO file : files) {
			imageUrls.add(file.getFilePath());

		}
		int totalComment = service.getCommentCountForBoard(boardId);

		if (page == 0) {
			page = 1;
		}
		int limitindex = (page - 1) * 5;
		int limitcount = 5;
		HashMap<String, Object> clistmap = new HashMap<String, Object>();
		clistmap.put("boardId", boardId);
		clistmap.put("limitindex", limitindex);
		clistmap.put("limitcount", limitcount);
		List<BoardCommentDTO> clist = service.getAllBoardComment(clistmap);

		List<BoardCommentDTO> replyList = service.getAllBoardReply(boardId);

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
			boolean hasLiked = service.hasUserLikedBoard(dto.getMemId(), boardId);

			mv.addObject("hasLiked", hasLiked);
		} else {
			mv.addObject("hasLiked", false);
		}
		int countLike = service.countLike(boardId);

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
		mv.setViewName("reviewpostdetail");
		return mv;
	}

	@RequestMapping("/insertReviewBoardComment")
	@ResponseBody
	public ResponseEntity<String> insertReviewBoardComment(
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
			int result = service.insertBoardComment(boarddto);
			if (result > 0) {
				// Update bcRef value for the newly inserted comment
				int insertedBcId = boarddto.getBcId(); // Assuming the bcId is set after insertion
				int updateResult = service.updateBcRef(insertedBcId);
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

	@RequestMapping("/insertReviewReplyComment")
	@ResponseBody
	public ResponseEntity<String> insertReviewReplyComment(
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
			int result = service.insertReplyComment(boarddto);

			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in");
		}
	}

	@RequestMapping("/insertReviewBoardLike")
	@ResponseBody
	public int 
ReviewBoardLike(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam("boardId") int boardId, HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user);
		if (dto != null) {
			// Check if the user has already liked the board
			boolean hasLiked = service.hasUserLikedBoard(dto.getMemId(), boardId);

			if (hasLiked) {
				// If the user has already liked the board, delete the like
				service.deleteBoardLike(dto.getMemId(), boardId);
			} else {
				// If the user has not liked the board, insert a new like
				BoardLikeDTO likedto = new BoardLikeDTO();
				likedto.setMemId(dto.getMemId());
				likedto.setBoardId(boardId);
				int result = service.insertBoardLike(likedto);
			}

			int updatedLikeCount = service.countLike(boardId);

			return updatedLikeCount;
		} else {
			// If the user is not logged in, redirect to the login page
			return 0;
		}

	}

	@RequestMapping("/reviewdeleteBoard")
	public String reviewdeleteBoard(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto, int boardId,
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

				List<FileDTO> files = service.getFilesByBoardId(boardId);
				for (FileDTO fileDTO : files) {
					File file = new File(savePath + fileDTO.getFilePath());
					if (file.exists()) {
						file.delete();
					}
				}

				service.deleteAllBoard(boardId);
				
				return "redirect:/main";
			} else {
				return "/login";
			}
		} else {
			return "/login";
		}
	}

	@RequestMapping("/reviewdeleteComment")
	public String reviewdeleteComment(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, int bcId, int boardId,
			String memId, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null) {
			if (dto.getMemType().equals("admin") || dto.getMemId().equals(memId)) {
				service.deleteComment(bcId);
				return "redirect:/reviewpostdetail?boardId=" + boardId + "&page=" + page;
			} else {
				return "/login";
			}
		} else {
			return "/login";
		}
	}

	@RequestMapping("/reviewdeleteReply")
	public String reviewdeleteReply(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, int bcId, int boardId,
			String memId, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		if (dto != null) {
			if (dto.getMemType().equals("admin") || dto.getMemId().equals(memId)) {
				service.deleteReply(bcId);
				return "redirect:/reviewpostdetail?boardId=" + boardId + "&page=" + page;
			} else {
				return "/login";
			}
		} else {
			return "/login";
		}
	}
}