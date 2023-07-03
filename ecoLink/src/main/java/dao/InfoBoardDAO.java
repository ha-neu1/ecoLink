package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;

@Mapper
@Repository
public interface InfoBoardDAO {
	
	public int getTotalBoard();
	public List<BoardDTO> boardList(int[] limit);
}
