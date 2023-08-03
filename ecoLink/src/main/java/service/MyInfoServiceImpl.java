package service;

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
	
	//public int deleteEnt(EnterpriseDTO edto) {
		//return dao.deleteEnt(edto);
	//}
	
	public List<EnterpriseDTO> getBrandBookmark(String memId){
		return dao.getBrandBookmark(memId);
	}
	
	public List<BoardDTO> getBoardLike(String memId){
		return dao.getBoardLike(memId);
	}
	
	public List<BoardDTO> getMyBoard(String memId){
		return dao.getMyBoard(memId);
	}
	
}
