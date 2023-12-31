package dao;

import java.util.HashMap;
import java.util.Map;

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
    //public String getLatestMemNickByType(String memType) {
	//	return session.selectOne("getLatestMemNickByType", memType);
	//}
    
    //id 중복여부
    public int isMemberIdExist(String inputId) {
    	return session.selectOne("isMemberIdExist", inputId);
    }
    
    //email 중복여부
    public int isMemberEmailExist(String inputEmail) {
    	return session.selectOne("isMemberEmailExist", inputEmail);
    }	
    
    //id 찾기
    public String findId(String memType, String memEmail) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("memType", memType);
        parameters.put("memEmail", memEmail);
        return session.selectOne("findId", parameters);
    }
    
    //pw 찾기
    public String findPwByEmail(String memType, String memEmail) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("memType", memType);
        parameters.put("memEmail", memEmail);
        return session.selectOne("findPwByEmail", parameters);
    };
    public String findPwById(String memType, String memId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("memType", memType);
        parameters.put("memId", memId);
        return session.selectOne("findPwById", parameters);
        };
}
