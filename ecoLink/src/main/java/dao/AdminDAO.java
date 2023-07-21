package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.AdminDTO;
import dto.BannerDTO;
import dto.BoardDTO;
import dto.MemberDTO;

@Repository
@Mapper
public interface AdminDAO {
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
	public int addBanner(BannerDTO dto);
	public int deleteBanner(String bannerId);
}
