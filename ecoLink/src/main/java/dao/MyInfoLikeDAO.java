package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.EnterpriseBookmarkDTO;
import dto.MemberDTO;

@Repository
public class MyInfoLikeDAO {
	@Autowired
	SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public List<EnterpriseBookmarkDTO> brandBookmark(String memId) {
		return session.selectList("brandBookmark", memId);
	}
	
	
}
