package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BannerDTO;
import dto.BoardDTO;
import dto.MainDTO;

@Repository
@Mapper
public interface MainDAO {
	public List<BannerDTO> getAllBanners();
	public List<MainDTO> getBrandList();
	public List<MainDTO> getShareBoardList();
	public int getMemberCount();
	public int getEnterCount();
	public int getBoardCount();
}
