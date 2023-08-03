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
	@Override
	public String generateNextMemNick(String memType) {
	    String memTypePrefix = (memType.equals("normal") ? "일반회원" : "기업회원");
	    String latestMemNick = dao.getLatestMemNickByType(memType);

	    if (latestMemNick == null) {
	        return memTypePrefix + "1";
	    } else {
	        int currentNumber = Integer.parseInt(latestMemNick.substring(memTypePrefix.length()));
	        return memTypePrefix + (currentNumber + 1);
	    }
	}
    
    //id중복여부
    public int isMemberIdExist(String inputId) {
    	return dao.isMemberIdExist(inputId);
    }
    
    //email중복여부
    public int isMemberEmailExist(String inputEmail) {
    	return dao.isMemberEmailExist(inputEmail);
    }	
	 
    /*
    //id 중복여부
    @Override
    public boolean isMemberIdExist(String memberId) {
        return dao.isMemberIdExist(memberId);
    }

    //email 중복여부
    @Override
    public boolean isMemberEmailExist(String memberEmail) {
        return dao.isMemberEmailExist(memberEmail);
    }

 // 회원가입
    @Override
    public void saveMemberInfo(MemberDTO memberDto) {
        if (memberDto.getMemType().equals("buis")) {
            // 기업 정보 저장을 위해 saveEnterpriseData 함수 호출
            saveEnterpriseData(null);
        } else {
            // 개인 정보만 저장하는 경우
            dao.saveMemberInfo(memberDto, null);
        }
    }

    // 기업 정보 저장 함수
    private void saveEnterpriseData(EnterpriseDTO enterpriseDto) {
        dao.saveEnterpriseInfo(enterpriseDto);
    }


    ///nick 카운트
	@Override
	public int isNickExists(String memNick) {
	    return dao.isNickExists(memNick);
	}
	*/
}
