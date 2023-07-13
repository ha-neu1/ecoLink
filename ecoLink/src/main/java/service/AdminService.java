package service;

import java.util.List;

import dto.AdminDTO;
import dto.BannerDTO;
import dto.BoardDTO;
import dto.MemberDTO;

public interface AdminService {
	public int getRegEnterConfirm();
	public int getAllRegUser();
	public List<BoardDTO> getSomeBoardList();
	public List<BoardDTO> getNewsBoardList();
	public List<BoardDTO> getShareBoardList();
	public List<BoardDTO> getReviewBoardList();
	public List<BoardDTO> getTipsBoardList();
	public List<AdminDTO> getUnConfirmedEnterList();
	public List<AdminDTO> getConfirmedEnterList();
	public List<BannerDTO> getAllBanner();
	public List<MemberDTO> getAllNormalMember();
	public List<MemberDTO> getAllAdminMember();
	public int addAdminAccount(MemberDTO dto);
	public int getSpecificAdminAccount(MemberDTO dto);
	public int deleteAdminAccount(String memId);
	public MemberDTO adminLogin(MemberDTO memberDTO);
}
