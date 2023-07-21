package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.EnterpriseDTO;
import dto.MemberDTO;

@Repository
public class MyInfoDAO {
	@Autowired
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public EnterpriseDTO getEntUser(String memId) {
		return session.selectOne("getEntUser", memId);
	}
	
	public int userUpdate(MemberDTO dto) {
		return session.update("userUpdate",dto);
	}
	
	public int entUpdate(EnterpriseDTO edto) {
		return session.update("entUpdate",edto);
	}
	
	public int deleteUser(String memId) {
		return session.delete("UserDelete",memId);
	}
	
}
