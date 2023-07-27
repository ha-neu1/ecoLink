package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.EnterpriseBookmarkDTO;
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
	
	public int deleteUser(MemberDTO dto) {
		return session.delete("userDelete",dto);
	}
	
	public int deleteEnt(EnterpriseDTO edto) {
		return session.delete("entDelete",edto);
	}
	
	public List<EnterpriseBookmarkDTO> brandBookmark(String memId) {
		return session.selectList("brandBookmark", memId);
	}
	
}
