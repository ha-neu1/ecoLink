package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BannerDTO;
import dto.BoardDTO;

@Repository
@Mapper
public interface MainDAO {
	public List<BannerDTO> getAllBanners();
	public List<BoardDTO> getShareBoardList();
	public int getMemberCount();
	public int getEnterCount();
	public int getBoardCount();
}
