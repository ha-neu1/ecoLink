package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;

@Mapper
@Repository
public interface InfoBoardDAO {

	public int getTotalBoard();

	public List<BoardDTO> boardListRecent(int[] limit);

	public List<BoardDTO> boardListView(int[] limit);

	public List<BoardDTO> searchList(HashMap map);

	public int getSearchBoard(HashMap map);

}
