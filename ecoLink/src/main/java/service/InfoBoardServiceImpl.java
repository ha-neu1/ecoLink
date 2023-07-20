package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.InfoBoardDAO;
import dto.BoardCommentDTO;
import dto.BoardDTO;

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
		return dao.insertBoard(dto);
	}
	@Override
	public BoardDTO updateViewcountAndGetDetail(int boardId) {
		int updaterows = dao.updateViewcount(boardId);
		return dao.getDetail(boardId);
	}
	
	
	
	
}
