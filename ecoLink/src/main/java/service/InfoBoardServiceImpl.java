package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.InfoBoardDAO;
import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.FileDTO;

@Service
public class InfoBoardServiceImpl implements InfoBoardService {
	@Autowired
	InfoBoardDAO dao;
	
	public int getTotalBoard() {
		return dao.getTotalBoard();
	}
	@Override
	public List<BoardDTO> boardListRecent(int[] limit) {
		 return dao.boardListRecent(limit);
	}
	
	@Override
	public List<BoardDTO> boardListView(int[] limit){
		return dao.boardListView(limit);
	}
	@Override
	public List<BoardDTO> searchList(HashMap map) {
		return dao.searchList(map);
	}
	@Override
	public int getSearchBoard(HashMap map) {
		return dao.getSearchBoard(map);
	}
	@Override
	public int insertBoard(BoardDTO dto) {
		int insertCount = dao.insertBoard(dto);
        int boardId = dao.getGeneratedBoardId(); // Get the generated boardId after inserting the board
        dto.setBoardId(boardId); // Set the boardId in the DTO for later use in the file insertion
        return insertCount;
	}
	@Override
	public BoardDTO updateViewcountAndGetDetail(int boardId) {
		int updaterows = dao.updateViewcount(boardId);
		return dao.getDetail(boardId);
	}
	@Override
	public int insertFile(FileDTO dto) {
		return dao.insertFile(dto);
	}
	@Override
	public int getGeneratedBoardId() {
		
		return dao.getGeneratedBoardId();
	}
	
	

	
	
	
	
	
	
}
