package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;
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
	public List<EnterpriseDTO> getBrandBookmark(String memId);
	public List<BoardDTO> getBoardLike(String memId);
	public List<BoardDTO> getMyBoard(String memId);
}
