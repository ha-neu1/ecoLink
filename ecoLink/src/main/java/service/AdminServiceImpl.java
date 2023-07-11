package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminDAO;
import dto.AdminDTO;
import dto.BannerDTO;
import dto.BoardDTO;
import dto.MemberDTO;

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

	@Override
	public List<BannerDTO> getAllBanner() {
		return dao.getAllBanner();
	}

	@Override
	public List<MemberDTO> getAllNormalMember() {
		return dao.getAllNormalMember();
	}

	@Override
	public List<MemberDTO> getAllAdminMember() {
		return dao.getAllAdminMember();
	}

	@Override
	public int addAdminAccount(MemberDTO dto) {
		return dao.addAdminAccount(dto);
	}

	@Override
	public int getSpecificAdminAccount(MemberDTO dto) {
		return dao.getSpecificAdminAccount(dto);
	}

	@Override
	public int deleteAdminAccount(String memId) {
		return dao.deleteAdminAccount(memId);
	}
	
	
}
