package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import dto.FileDTO;
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
	public ModelAndView infoboardlist(@RequestParam(value = "page", required = false, defaultValue = "1") int page,

			@RequestParam(value = "selectValue", required = false, defaultValue = "recent") String selectValue) {
		int totalBoard = service.getTotalBoard();

		int limitcount = 5;
		int limitindex = (page - 1) * limitcount;
		int limit[] = new int[2];
		limit[0] = limitindex;
		limit[1] = limitcount;

		List<BoardDTO> boardList = service.boardListRecent(limit);

		if (selectValue.equals("recent")) {
			boardList = service.boardListRecent(limit);
		} else if (selectValue.equals("view")) {
			boardList = service.boardListView(limit);
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("totalBoard", totalBoard);
		mv.addObject("boardList", boardList);
		mv.setViewName("infoarticle");
		return mv;
	}

	@RequestMapping("/infoboardsearch")
	public ModelAndView infoboardsearch(

			@RequestParam(value = "item", required = false, defaultValue = "seartch_all") String item,

			@RequestParam(value = "word", required = false, defaultValue = "") String word,

			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		int limitcount = 5;
		int limitindex = (page - 1) * limitcount;

		HashMap map = new HashMap();
		if (item.equals("seartch_all")) {
			item = null;
		}
		map.put("colname", item);
		map.put("colvalue", "%" + word + "%");
		map.put("limitindex", limitindex);
		map.put("limitcount", limitcount);
		List<BoardDTO> searchlist = service.searchList(map);
		int searchcount = service.getSearchBoard(map);

		ModelAndView mv = new ModelAndView();
		mv.addObject("boardList", searchlist);
		mv.addObject("totalBoard", searchcount);
		mv.setViewName("infoboardsearch");
		return mv;
	}

	@GetMapping("/infowriting")
	public String infowriting() {
		return "infowritingform";
	}

	@PostMapping("/infowriting")
	public ModelAndView writeprocess(BoardDTO dto,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws IOException {
		String savePath = "c:/kdt/upload/";

		List<MultipartFile> files = dto.getFiles();
		int insertcount = service.insertBoard(dto);
		if (files != null && !files.isEmpty()) {
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					String originalname1 = file.getOriginalFilename();
					String beforeext1 = originalname1.substring(0, originalname1.indexOf("."));
					String ext1 = originalname1.substring(originalname1.indexOf("."));

					String newFilename = beforeext1 + "(" + UUID.randomUUID().toString() + ")" + ext1;
					File newFile = new File(savePath + newFilename);
					file.transferTo(newFile);

					FileDTO fileDTO = new FileDTO();
					fileDTO.setFileIdx(UUID.randomUUID().toString());
					fileDTO.setFilePath("c:/kdt/upload/" + newFilename);
					fileDTO.setFileName(originalname1);
					fileDTO.setFileType(file.getContentType());

					fileDTO.setBoardId(dto.getBoardId());
					int boardId = dto.getBoardId();

					fileDTO.setBoardId(boardId);
					int insertfile = service.insertFile(fileDTO);

				}
			}
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
		List<BoardDTO> boardList = service.boardListRecent(limit); // 예시: 최신순으로 게시물 목록을 가져오는 로직
		mv.addObject("boardList", boardList);
		mv.setViewName("infoarticle");
		return mv;
	}

	@RequestMapping("/infopostdetail")
	public ModelAndView infopostdetail(@RequestParam(name = "boardId") Integer boardId) {
		ModelAndView mv = new ModelAndView();

		if (boardId == null) {
			mv.addObject("error", "Invalid boardId"); // 오류 메시지 추가
			mv.setViewName("error-page"); // 오류 페이지로 이동하도록 설정
			return mv;
		}

		BoardDTO dto = service.updateViewcountAndGetDetail(boardId);
		mv.addObject("detaildto", dto);

		mv.setViewName("infopostdetail");
		return mv;
	}

}
