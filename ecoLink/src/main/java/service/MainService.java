package service;

import java.util.List;

import dto.BannerDTO;
import dto.BoardDTO;

public interface MainService {
	public List<BannerDTO> getAllBanners();

	public List<BoardDTO> getShareBoardList();

	public int getMemberCount();

	public int getEnterCount();

	public int getBoardCount();
}
