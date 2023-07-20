package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MyInfoLikeDAO;
import dto.EnterpriseBookmarkDTO;
import dto.EnterpriseDTO;
import dto.MemberDTO;

@Service
public class MyInfoLikeService {
	@Autowired
	MyInfoLikeDAO dao;
	 
	public List<EnterpriseBookmarkDTO> brandBookmark(String memId){
		return dao.brandBookmark(memId);
	}
	
	/*
	 * public EnterpriseDTO getbrandbyId(String entCrn) { return
	 * dao.getbrandbyId(entCrn); }
	 */
	
}
