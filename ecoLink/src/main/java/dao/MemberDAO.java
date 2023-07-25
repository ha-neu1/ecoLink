package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.EnterpriseDTO;
import dto.MemberDTO;

@Repository
public class MemberDAO {

    @Autowired
    SqlSession session;
    
//    //회원 정보 조회 - 로그인 시 활용
//	public MemberDTO oneMember(String id) {
//		return session.selectOne("oneMember", id); 
//	}
	
	//로그인
	public MemberDTO login(MemberDTO memberDTO) {
		return session.selectOne("login", memberDTO);
	}
	
	//회원가입
    public void addMember(MemberDTO member) {
        session.insert("addMember", member);
    }
    
    public void addEnterprise(EnterpriseDTO enter) {
    	  session.insert("addEnterprise", enter);
    }
    
    //닉네임 설정
    public String getLatestMemNickByType(String memType) {
		return session.selectOne("getLatestMemNickByType", memType);
	}
    
    //id 중복여부
    public int isMemberIdExist(String inputId) {
    	return session.selectOne("isMemberIdExist", inputId);
    }
    
    //email 중복여부
    public int isMemberEmailExist(String inputEmail) {
    	return session.selectOne("isMemberEmailExist", inputEmail);
    }	
	
	/*
	//id 중복여부
	public boolean isMemberIdExist(String memberId) {
		int count = session.selectOne("member.isMemberIdExist", memberId);
        return count > 0;
	}

	//email 중복여부
    public boolean isMemberEmailExist(String memberEmail) {
    	int count = session.selectOne("member.isMemberEmailExist", memberEmail);
        return count > 0;
	}

    // 회원가입 / member 데이터 저장
    public void saveMemberInfo(MemberDTO memberDto, EnterpriseDTO enterpriseDto) {
        session.insert("member.saveMemberInfo", memberDto);
        if (memberDto.getMemType().equals("buis")) {
            session.insert("enterprise.saveEnterpriseInfo", enterpriseDto);
        }
    }

    // 회원가입 / enterprise 데이터 저장
    public void saveEnterpriseInfo(EnterpriseDTO enterpriseDto) {
        session.insert("enterprise.saveEnterpriseInfo", enterpriseDto);
    }


    
    //nick 카운트
    public int isNickExists(String inputNick) {
        return session.selectOne("isNickExists", inputNick);
    }
    */
}
