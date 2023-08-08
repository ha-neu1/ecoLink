package service;

import java.util.List;

import dto.BannerDTO;
import dto.BoardDTO;
import dto.MainDTO;

public interface MainService {
	public List<BannerDTO> getAllBanners();
	
	public List<MainDTO> getBrandList();

	public List<MainDTO> getShareBoardList(String savePath);

	public int getMemberCount();

	public int getEnterCount();

	public int getBoardCount();
}
