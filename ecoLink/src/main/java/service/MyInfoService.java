package service;

import java.util.HashMap;
import java.util.List;

import dto.BoardDTO;
import dto.EnterpriseDTO;
import dto.MemberDTO;

public interface MyInfoService {
	public EnterpriseDTO getEntUser(String memId);
	
	public MemberDTO getUser(String memId);

	public int userUpdate(MemberDTO dto);

	public int entUpdate(EnterpriseDTO edto);

	public int deleteUser(MemberDTO dto);

	public int deleteEnt(EnterpriseDTO edto);

	public List<EnterpriseDTO> getBrandBookmark(HashMap<String, Object> clistmap);

	public int getBookmarkCount(String memId);

	public List<BoardDTO> getBoardLike(HashMap<String, Object> clistmap);

	public int getBoardLikeCount(String memId);

	public List<BoardDTO> getMyBoard(HashMap<String, Object> clistmap);

	public int getMyBoardCount(String memId);

}
