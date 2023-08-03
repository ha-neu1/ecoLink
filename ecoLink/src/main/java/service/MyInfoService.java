package service;

import java.util.List;

import dto.BoardDTO;
import dto.EnterpriseDTO;
import dto.MemberDTO;

public interface MyInfoService {
	public EnterpriseDTO getEntUser(String memId);
	public int userUpdate(MemberDTO dto);
	public int entUpdate(EnterpriseDTO edto);
	public int deleteUser(MemberDTO dto);
	//public int deleteEnt(EnterpriseDTO edto);
	public List<EnterpriseDTO> getBrandBookmark(String memId);
	public List<BoardDTO> getBoardLike(String memId);
	public List<BoardDTO> getMyBoard(String memId);

}
