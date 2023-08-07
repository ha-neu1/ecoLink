package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;
import dto.BrandCommentDTO;
import dto.EnterpriseBookmarkDTO;
import dto.EnterpriseDTO;
import dto.MemberDTO;

@Repository
@Mapper
public interface MyInfoDAO {
	public EnterpriseDTO getEntUser(String memId);
	
	public MemberDTO getUser(String memId);

	public int userUpdate(MemberDTO dto);

	public int entUpdate(EnterpriseDTO edto);

	public int deleteUser(MemberDTO dto);
	
	public int deleteLike(String memId);
	
	public int deleteUBM(String memId);

	public int deleteEnt(EnterpriseDTO edto);
	
	public int deleteBC(String memId);
	
	public int deleteEBM(String memId);

	public List<EnterpriseDTO> getBrandBookmark(HashMap<String, Object> clistmap);

	public int getBookmarkCount(String memId);

	public List<BoardDTO> getBoardLike(HashMap<String, Object> clistmap);

	public int getBoardLikeCount(String memId);

	public List<BoardDTO> getMyBoard(HashMap<String, Object> clistmap);

	public int getMyBoardCount(String memId);
}
