package service;

import java.util.List;

import dto.BoardDTO;

public interface InfoBoardService {
	public int getTotalBoard();
	public List<BoardDTO> boardListRecent(int[] limit);
	public List<BoardDTO> boardListView(int[] limit);
}
