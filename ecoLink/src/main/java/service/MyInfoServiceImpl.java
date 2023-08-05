package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MyInfoDAO;
import dto.BoardDTO;
import dto.EnterpriseDTO;
import dto.MemberDTO;

@Service
public class MyInfoServiceImpl implements MyInfoService {
	@Autowired
	MyInfoDAO dao;

	public EnterpriseDTO getEntUser(String memId) {
		return dao.getEntUser(memId);
	}

	public int userUpdate(MemberDTO dto) {
		return dao.userUpdate(dto);
	}

	public int entUpdate(EnterpriseDTO edto) {
		return dao.entUpdate(edto);
	}

	public int deleteUser(MemberDTO dto) {
		return dao.deleteUser(dto);
	}

	public int deleteEnt(EnterpriseDTO edto) {
		return dao.deleteEnt(edto);
	}

	public List<EnterpriseDTO> getBrandBookmark(HashMap<String, Object> clistmap) {
		return dao.getBrandBookmark(clistmap);
	}

	public int getBookmarkCount(String memId) {
		return dao.getBookmarkCount(memId);
	}

	public List<BoardDTO> getBoardLike(HashMap<String, Object> clistmap) {
		return dao.getBoardLike(clistmap);
	}

	public int getBoardLikeCount(String memId) {
		return dao.getBoardLikeCount(memId);
	}

	public List<BoardDTO> getMyBoard(HashMap<String, Object> clistmap) {
		return dao.getMyBoard(clistmap);
	}

	public int getMyBoardCount(String memId) {
		return dao.getMyBoardCount(memId);
	}

}
