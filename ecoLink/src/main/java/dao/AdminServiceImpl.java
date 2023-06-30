package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AdminDTO;
import dto.BoardDTO;
import service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAO dao;

	@Override
	public int getRegEnterConfirm() {
		return dao.getRegEnterConfirm();
	}

	@Override
	public int getAllRegUser() {
		return dao.getAllRegUser();
	}

	@Override
	public List<BoardDTO> getSomeBoardList() {
		return dao.getSomeBoardList();
	}
	
	@Override
	public List<BoardDTO> getNewsBoardList() {
		return dao.getNewsBoardList();
	}

	@Override
	public List<BoardDTO> getShareBoardList() {
		return dao.getShareBoardList();
	}

	@Override
	public List<BoardDTO> getReviewBoardList() {
		return dao.getReviewBoardList();
	}

	@Override
	public List<BoardDTO> getTipsBoardList() {
		return dao.getTipsBoardList();
	}

	@Override
	public List<AdminDTO> getUnConfirmedEnterList() {
		return dao.getUnConfirmedEnterList();
	}

	@Override
	public List<AdminDTO> getConfirmedEnterList() {
		return dao.getConfirmedEnterList();
	}
	
	
}
