package service;

import java.util.List;

import dto.BoardDTO;

public interface InfoBoardService {
	public int getTotalBoard();
	public List<BoardDTO> boardList(int[] limit);
	//public List<BoardDTO> boardListViews(int[] limit);
}
