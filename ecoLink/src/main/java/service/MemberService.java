package service;

import dto.EnterpriseDTO;
import dto.MemberDTO;

public interface MemberService {
//	// 회원 정보 조회
//	MemberDTO oneMember(String id);
	
	// 회원가입
	void addMember(MemberDTO member);
	void addEnterprise(EnterpriseDTO enter);
	
	// 닉네임 설정
	//String generateNextMemNick(String memType);
	
	//로그인
	public MemberDTO login(MemberDTO memberDTO);
	
    //id중복여부
    public int isMemberIdExist(String inputId);
    
    //email중복여부
    public int isMemberEmailExist(String inputEmail);
    
    //id 찾기
    public String findId(String memType, String memEmail);

    //pw 찾기
    public String findPwByEmail(String memType, String memEmail);
    public String findPwById(String memType, String memId);
}
