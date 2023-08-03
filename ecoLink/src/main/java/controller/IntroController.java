package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardLikeDTO;
import dto.FileDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.InfoBoardService;

@Controller
public class IntroController {
	@Autowired
	@Qualifier("infoBoardServiceImpl")
	InfoBoardService service;

	private static final Logger logger = Logger.getLogger(IntroController.class.getName());

	@RequestMapping("/introboard")
	public String introboard(HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {
		return "introboard";
	}

	@RequestMapping("/infoboardlist")
	public ModelAndView infoboardlist(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "selectValue", required = false, defaultValue = "recent") String selectValue,
			HttpServletResponse response, HttpSession session) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		int totalBoard = service.getTotalBoard();

		int limitcount = 5;
		int limitindex = (page - 1) * limitcount;
		int limit[] = new int[2];
		limit[0] = limitindex;
		limit[1] = limitcount;

		List<BoardDTO> boardList;
		if (selectValue.equals("recent")) {
			boardList = service.boardListRecent(limit);
		} else if (selectValue.equals("view")) {
			boardList = service.boardListView(limit);
		} else {
			boardList = service.boardListRecent(limit); // recent가 디폴트
		}

		Map<Integer, String> imageMap = new HashMap<>();
		for (BoardDTO boarddto : boardList) {
			List<FileDTO> files = service.getFilesByBoardId(boarddto.getBoardId());
			if (files != null && !files.isEmpty()) {
				FileDTO firstFile = files.get(0);
				String imageUrl = firstFile.getFilePath() + "/" + firstFile.getFileName();

				// c:/kdt/upload/를 "/upload/"로 바꿈
				if (imageUrl.startsWith("c:/kdt/upload/")) {
					imageUrl = imageUrl.replaceFirst("c:/kdt/upload/", "/upload/");
				} else {
					imageUrl = "/upload/" + imageUrl;
				}

				imageMap.put(boarddto.getBoardId(), imageUrl);
			} else {
				// 이미지 없을 떄
				imageMap.put(boarddto.getBoardId(), "/upload/noimage.png");
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		mv.addObject("totalBoard", totalBoard);
		mv.addObject("boardList", boardList);
		mv.addObject("imageMap", imageMap);
		mv.setViewName("infoarticle");
		return mv;
	}

	@RequestMapping("/infoboardsearch")
	public ModelAndView infoboardsearch(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,

			@RequestParam(value = "item", required = false, defaultValue = "search_all") String item,

			@RequestParam(value = "word", required = false, defaultValue = "") String word,

			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletResponse response,
			HttpSession session) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		int limitcount = 5;
		int limitindex = (page - 1) * limitcount;

		HashMap map = new HashMap();
		if (item.equals("search_all")) {
			item = null;
		}
		map.put("colname", item);
		map.put("colvalue", "%" + word + "%");
		map.put("limitindex", limitindex);
		map.put("limitcount", limitcount);
		List<BoardDTO> searchlist = service.searchList(map);
		int searchcount = service.getSearchBoard(map);
		Map<Integer, String> imageMap = new HashMap<>();
		for (BoardDTO boarddto : searchlist) {
			List<FileDTO> files = service.getFilesByBoardId(boarddto.getBoardId());
			if (files != null && !files.isEmpty()) {
				FileDTO firstFile = files.get(0);
				String imageUrl = firstFile.getFilePath() + "/" + firstFile.getFileName();

				// c:/kdt/upload/를 "/upload/"로 바꿈
				if (imageUrl.startsWith("c:/kdt/upload/")) {
					imageUrl = imageUrl.replaceFirst("c:/kdt/upload/", "/upload/");
				} else {
					imageUrl = "/upload/" + imageUrl;
				}

				imageMap.put(boarddto.getBoardId(), imageUrl);
			} else {
				// 이미지 없을 때
				imageMap.put(boarddto.getBoardId(), "/upload/noimage.png");
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", dto);
		mv.addObject("boardList", searchlist);
		mv.addObject("totalBoard", searchcount);
		mv.addObject("imageMap", imageMap);
		mv.setViewName("infoboardsearch");
		return mv;
	}

	@GetMapping("/infowriting")
	public String infowriting(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			HttpServletResponse response, HttpSession session, Model model) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		MemberDTO user = (MemberDTO) session.getAttribute("logininfo"); // 로그인 정보를 가져와서 MemberDTO로 캐스팅
		model.addAttribute("user", user); // Model에 사용자 정보를 추가) {
		return "infowritingform";
	}

	@PostMapping("/infowriting")
	public ModelAndView writeprocess(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			BoardDTO boarddto, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpServletResponse response, HttpSession session) throws IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		String savePath = "c:/kdt/upload/";

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
					fileDTO.setFilePath("c:/kdt/upload/" + newFilename);
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
					fileDTO.setFilePath("c:/kdt/upload/" + newFilename);
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

		int totalBoard = service.getTotalBoard();
		mv.addObject("totalBoard", totalBoard);

		// 게시물 목록 조회 로직
		int limitcount = 5;
		int limitindex = (page - 1) * limitcount;
		int limit[] = new int[2];
		limit[0] = limitindex;
		limit[1] = limitcount;
		List<BoardDTO> boardList = service.boardListRecent(limit);
		// 최신순으로 게시물 목록을 가져오는 로직
		mv.addObject("user", dto);
		mv.addObject("insertcount", insertcount);
		mv.addObject("boardList", boardList);
		mv.setViewName("infoarticle");
		return mv;
	}

	@RequestMapping("/infopostdetail")
	public ModelAndView infopostdetail(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
			@RequestParam(name = "boardId") Integer boardId,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletResponse response,
			HttpSession session, HttpServletRequest request, String focus) {
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
		session = request.getSession();
		if (focus != null) {
			if (focus.equals("true")) {
				session.setAttribute("focus", "true");
			} else {
				session.setAttribute("focus", "false");
			}
		} else {
			session.setAttribute("focus", "false");
		}
		if (dto != null) {
	        boolean hasLiked = service.hasUserLikedBoard(dto.getMemId(), boardId);
	        logger.info("Value of hasLiked: " + hasLiked);
	        mv.addObject("hasLiked", hasLiked);
	    } else {
	        mv.addObject("hasLiked", false) ;
	    }
		
		mv.addObject("currentCpage", page);
		mv.addObject("totalPage", totalPage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("clist", clist);
		mv.addObject("user", dto);
		mv.addObject("imageUrls", imageUrls);
		mv.addObject("bId", boardId);
		mv.setViewName("infopostdetail");
		return mv;
	}

	@RequestMapping("/insertBoardComment")
	public ResponseEntity<String> insertBoardComment(
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
			logger.info("Inserting comment: " + boarddto.toString());
			int result = service.insertBoardComment(boarddto);
			if (result > 0) {
				// Update bcRef value for the newly inserted comment
				int insertedBcId = boarddto.getBcId(); // Assuming the bcId is set after insertion
				int updateResult = service.updateBcRef(insertedBcId);
				if (updateResult > 0) {
					System.out.println("bcRef updated successfully");
				} else {
					System.out.println("Failed to update bcRef");
				}
			} else {
				System.out.println("Failed to insert comment");
			}
			return ResponseEntity.ok().build();
		} else {
			System.out.println("User is not logged in. Redirecting to /login.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in");
		}
	}

	@RequestMapping("/insertReplyComment")
	public ResponseEntity<String> insertReplyComment(
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
			logger.info("Inserting comment: " + boarddto.toString());
			int result = service.insertReplyComment(boarddto);

			return ResponseEntity.ok().build();
		} else {
			System.out.println("User is not logged in. Redirecting to /login.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in");
		}
	}

	@RequestMapping("/insertBoardLike")
	public String insertBoardLike(@SessionAttribute(name = "logininfo", required = false) MemberDTO dto,
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

			// Redirect to the infopostdetail page after handling the like action
			return "redirect:/infopostdetail?boardId=" + boardId;
		} else {
			// If the user is not logged in, redirect to the login page
			return "/login";
		}

	}
}
