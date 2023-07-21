package service;

import java.util.HashMap;
import java.util.List;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.FileDTO;

public interface InfoBoardService {
	public int getTotalBoard();

	public List<BoardDTO> boardListRecent(int[] limit);

	public List<BoardDTO> boardListView(int[] limit);

	public List<BoardDTO> searchList(HashMap map);

	public int getSearchBoard(HashMap map);
	
	public int insertBoard(BoardDTO dto);
	
	public BoardDTO updateViewcountAndGetDetail(int boardId);
	
	public int insertFile(FileDTO dto);
	
	public int getGeneratedBoardId(); 
	
	
	
}
