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
	
	// 닉네임 설정
	/*@Override
	public String generateNextMemNick(String memType) {
	    String memTypePrefix = (memType.equals("normal") ? "일반회원" : "기업회원");
	    String latestMemNick = dao.getLatestMemNickByType(memType);

	    if (latestMemNick == null) {
	        return memTypePrefix + "1";
	    } else {
	        int currentNumber = Integer.parseInt(latestMemNick.substring(memTypePrefix.length()));
	        return memTypePrefix + (currentNumber + 1);
	    }
	}*/
    
    //id중복여부
    public int isMemberIdExist(String inputId) {
    	return dao.isMemberIdExist(inputId);
    }
    
    //email중복여부
    public int isMemberEmailExist(String inputEmail) {
    	return dao.isMemberEmailExist(inputEmail);
    }	
 
}
