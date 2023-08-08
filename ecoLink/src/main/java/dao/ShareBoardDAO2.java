package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;




@Repository
@Mapper
public interface ShareBoardDAO2 {
	public List<BoardDTO> getNormalBPList(HashMap<String, Object> clistmap);
	public List<BoardDTO> getviewcountBPList(HashMap<String, Object> clistmap);
	public int getBPListCount();
	public List<BoardDTO> getoptionBPList(HashMap<String, Object> clistmap);
	public int getoptionBPListCount(String memNick);
	

	
}
