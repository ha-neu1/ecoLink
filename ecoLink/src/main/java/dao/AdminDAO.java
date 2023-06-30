package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.AdminDTO;
import dto.BoardDTO;

@Repository
@Mapper
public interface AdminDAO {
	public int getRegEnterConfirm();
	public int getAllRegUser();
	public List<BoardDTO> getSomeBoardList();
	public List<BoardDTO> getNewsBoardList();
	public List<BoardDTO> getShareBoardList();
	public List<BoardDTO> getReviewBoardList();
	public List<BoardDTO> getTipsBoardList();
	public List<AdminDTO> getUnConfirmedEnterList();
	public List<AdminDTO> getConfirmedEnterList();
}
