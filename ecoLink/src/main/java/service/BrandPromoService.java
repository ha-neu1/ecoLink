package service;

import java.util.HashMap;
import java.util.List;

import dto.BrandCommentDTO;
import dto.BrandPromoDTO;
import dto.BrandPromoListDTO;

public interface BrandPromoService {
	public BrandPromoDTO getBrandPromoDetail(String entCrn);
	public int getBrandPromoBookmark(String memId, String entCrn);
	public int deleteBrandPromoBookmark(String memId, String entCrn);
	public int insertBrandPromoBookmark(String memId, String entCrn);
	public double getCommentAvgRate(String entCrn);
	public int insertBrandComment(BrandCommentDTO bdto);
	public List<BrandCommentDTO> getAllBrandComment(HashMap<String, Object> clistmap);
	public int getCommentCount(String entCrn);
	public int deleteBrandComment(String entCrn, String memId);
	public int updateBrandComment(BrandCommentDTO bdto);
	public List<BrandPromoListDTO> getNormalBPList(HashMap<String, Object> clistmap);
	public List<BrandPromoListDTO> getrateBPList(HashMap<String, Object> clistmap);
	public List<BrandPromoListDTO> getoptionBPList(HashMap<String, Object> clistmap);
	public int getBPListCount();
	public int getoptionBPListCount(String memNick);
	
}
