package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.EnterpriseBookmarkDTO;
import dto.EnterpriseDTO;
import dto.MemberDTO;

@Repository
@Mapper
public interface MyInfoDAO {
	public EnterpriseDTO getEntUser(String memId);
	public int userUpdate(MemberDTO dto);
	public int entUpdate(EnterpriseDTO edto);
	public int deleteUser(MemberDTO dto);
	//public int deleteEnt(EnterpriseDTO edto);
	public List<EnterpriseBookmarkDTO> brandBookmark(String memId);
}
