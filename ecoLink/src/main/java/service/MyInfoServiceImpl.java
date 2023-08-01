package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MyInfoDAO;
import dto.EnterpriseBookmarkDTO;
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
	
	public List<EnterpriseBookmarkDTO> brandBookmark(String memId){
		return dao.brandBookmark(memId);
	}
	
	/*
	 * public EnterpriseDTO getbrandbyId(String entCrn) { return
	 * dao.getbrandbyId(entCrn); }
	 */
}
