package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MyInfoDAO;
import dto.MemberDTO;

@Service
public class MyInfoService {
	@Autowired
	MyInfoDAO dao;
	
	public MemberDTO getUser(String memId) {
		return dao.getUser(memId);
	}
	
	public int userUpdate(MemberDTO dto) {
		return dao.userUpdate(dto);
	}
	
	public int deleteUser(String memId) {
		return dao.deleteUser(memId);
	}
}
