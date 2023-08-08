package service;

import java.util.HashMap;
import java.util.List;

import dto.BoardDTO;

public interface ShareBoardService2 {
	public List<BoardDTO> getNormalBPList(HashMap<String, Object> clistmap);
	public List<BoardDTO> getviewcountBPList(HashMap<String, Object> clistmap);
	public int getBPListCount();
	public List<BoardDTO> getoptionBPList(HashMap<String, Object> clistmap);
	public int getoptionBPListCount(String memNick);
	
	
}
