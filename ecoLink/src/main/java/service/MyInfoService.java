package service;

import org.springframework.beans.factory.annotation.Autowired;

import dao.MyInfoDAO;
import dto.MemberDTO;

public class MyInfoService {
	@Autowired
	MyInfoDAO dao;
	
	public MemberDTO getUser(String memId) {
		return dao.getUser(memId);
	}
	
	public int memberUpdate(MemberDTO dto) {
		return dao.memberUpdate(dto);
	}
	
	
}
