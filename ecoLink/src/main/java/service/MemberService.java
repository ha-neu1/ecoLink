package service;

import dto.EnterpriseDTO;
import dto.MemberDTO;

public interface MemberService {
//	// 회원 정보 조회
//	MemberDTO oneMember(String id);
	
	// 회원가입
	void addMember(MemberDTO member);
	void addEnterprise(EnterpriseDTO enter);
	
	//닉네임 설정
	String getLatestMemNickByType(String memType);
	String generateNextMemNick(String memType, String latestMemNick);
	
	//로그인
	public MemberDTO login(MemberDTO memberDTO);
	
    //id중복여부
    public int isMemberIdExist(String inputId);
    
    //email중복여부
    public int isMemberEmailExist(String inputEmail);
    	
	
    /*
    // id 중복 여부 확인
    boolean isMemberIdExist(String memberId);
    
    // email 중복 여부 확인
    boolean isMemberEmailExist(String memberEmail);
    
    // 회원가입
    void saveMemberInfo(MemberDTO memberDto);
    
    // nick 카운트
    int isNickExists(String inputNick);
    */
}
