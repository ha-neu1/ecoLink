package service;

import java.util.List;

import dto.AdminDTO;
import dto.BoardDTO;

public interface AdminService {
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
