package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MyInfoDAO;
import dto.EnterpriseBookmarkDTO;
import dto.EnterpriseDTO;
import dto.MemberDTO;

@Service
public class MyInfoService {
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
	
	public int deleteUser(String memId) {
		return dao.deleteUser(memId);
	}
	
	public int deleteEnt(String memId) {
		return dao.deleteEnt(memId);
	}
	
	public List<EnterpriseBookmarkDTO> brandBookmark(String memId){
		return dao.brandBookmark(memId);
	}
	
	/*
	 * public EnterpriseDTO getbrandbyId(String entCrn) { return
	 * dao.getbrandbyId(entCrn); }
	 */
}
