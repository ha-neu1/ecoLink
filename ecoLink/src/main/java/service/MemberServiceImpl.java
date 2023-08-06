package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDAO;
import dto.EnterpriseDTO;
import dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
    
	@Autowired
	MemberDAO dao;
    
//	// 회원 정보 조회
//	@Override
//	public MemberDTO oneMember(String id) {
//		return dao.oneMember(id);
//	}
	
	//로그인
	@Override
	public MemberDTO login(MemberDTO memberDTO) {
		return dao.login(memberDTO);
	}
	
	// 회원가입
	public void addMember(MemberDTO member) {
	    dao.addMember(member);
	}
	
	public void addEnterprise(EnterpriseDTO enter) {
		  dao.addEnterprise(enter);
		}
    
    //id중복여부
    public int isMemberIdExist(String inputId) {
    	return dao.isMemberIdExist(inputId);
    }
    
    //email중복여부
    public int isMemberEmailExist(String inputEmail) {
    	return dao.isMemberEmailExist(inputEmail);
    }	
    
    //id 찾기
    @Override
    public String findId(String memType, String memEmail) {
        return dao.findId(memType, memEmail);
    }
    
    //pw 찾기
    @Override
    public String findPwByEmail(String memType, String memEmail) {
        return dao.findPwByEmail(memType, memEmail);
    }

    @Override
    public String findPwById(String memType, String memId) {
        return dao.findPwById(memType, memId);
    }
 
}
