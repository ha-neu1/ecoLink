package service;

import java.util.HashMap;
import java.util.List;

import dto.BoardDTO;

public interface InfoBoardService {
	public int getTotalBoard();

	public List<BoardDTO> boardListRecent(int[] limit);

	public List<BoardDTO> boardListView(int[] limit);

	public List<BoardDTO> searchList(HashMap map);

	public int getSearchBoard(HashMap map);
}
