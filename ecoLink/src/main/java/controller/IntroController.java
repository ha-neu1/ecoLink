package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.FileDTO;
import dto.MemberDTO;
import service.InfoBoardService;


@Controller
public class IntroController {
	@Autowired
	@Qualifier("infoBoardServiceImpl")
	InfoBoardService service;

	@RequestMapping("/introboard")
	public String introboard() {
		return "introboard";
	}

	@RequestMapping("/infoboardlist")
	public ModelAndView infoboardlist(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto,@RequestParam(value = "page", required = false, defaultValue = "1") int page,
	        @RequestParam(value = "selectValue", required = false, defaultValue = "recent") String selectValue) {
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

	            // c:/kdt/upload/를  "/upload/"로 바꿈
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
	    mv.addObject("totalBoard", totalBoard);
	    mv.addObject("boardList", boardList);
	    mv.addObject("imageMap", imageMap); 
	    mv.setViewName("infoarticle");
	    return mv;
	}


	@RequestMapping("/infoboardsearch")
	public ModelAndView infoboardsearch(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto,

			@RequestParam(value = "item", required = false, defaultValue = "search_all") String item,

			@RequestParam(value = "word", required = false, defaultValue = "") String word,

			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
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

	            // c:/kdt/upload/를  "/upload/"로 바꿈
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
		mv.addObject("boardList", searchlist);
		mv.addObject("totalBoard", searchcount);
		mv.addObject("imageMap", imageMap); 
		mv.setViewName("infoboardsearch");
		return mv;
	}
	

	@GetMapping("/infowriting")
	public String infowriting(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto) {
		return "infowritingform";
	}

	@PostMapping("/infowriting")
	public ModelAndView writeprocess(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto,BoardDTO boarddto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws IOException {
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
		//  최신순으로 게시물 목록을 가져오는 로직
		mv.addObject("insertcount", insertcount);
		mv.addObject("boardList", boardList);
		mv.setViewName("infoarticle");
		return mv;
	}

	@RequestMapping("/infopostdetail")
	public ModelAndView infopostdetail(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto,@RequestParam(name = "boardId") Integer boardId) {
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
		    mv.addObject("imageUrls", imageUrls);

		mv.setViewName("infopostdetail");
		return mv;
	}
	@RequestMapping("/insertBoardComment")
	public String insertBoardComment(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, String comment, String boardId) {
		if(dto != null) {
			BoardCommentDTO boarddto = new BoardCommentDTO();
			boarddto.setBcContents(comment.replace("\r\n","<br>"));
			boarddto.setMemId(dto.getMemId());
			boarddto.setBoardId(Integer.parseInt(boardId));
			int result = service.insertBoardComment(boarddto);
			return "redirect:/infopostdetail?boardId=" + boardId;
		}else {
			return "/login";
		}
	}
	@RequestMapping("/getBoardComments")
	@ResponseBody
	public String getBoardComments(@RequestParam(name = "boardId") Integer boardId,@RequestParam(value="page", required=false, defaultValue = "1") int page) {
		 System.out.println("Received Board ID: " + boardId);
	    
	  
	    int totalComment = service.getCommentCount(Integer.toString(boardId));
	    int limitindex = (page - 1) * 5;
	    int limitcount = 5;
	    HashMap<String, Object> paramMap = new HashMap<>();
	    paramMap.put("boardId", boardId);
	    paramMap.put("limitindex", limitindex);
	    paramMap.put("limitcount", limitcount);
	    List<BoardCommentDTO> comments = service.getAllBoardComment(paramMap);
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
		 HashMap<String, Object> result = new HashMap<>();
		    result.put("comments", comments);
		    result.put("page", page);
		    result.put("totalPage", totalPage);
		    result.put("startpage", startpage);
		    result.put("endpage", endpage);
	    ObjectMapper objectMapper = new ObjectMapper();
	    String jsonComments;
	    try {
	        jsonComments = objectMapper.writeValueAsString(result);
	    } catch (JsonProcessingException e) {
	        // Handle the exception, log the error, etc.
	        jsonComments = "[]"; // Return an empty JSON array as a fallback
	    }
	    return jsonComments;   
	}
	
	

}
