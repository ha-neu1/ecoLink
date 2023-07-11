package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.InfoBoardDAO;
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
}
