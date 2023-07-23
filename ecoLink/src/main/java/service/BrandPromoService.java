package service;

import dto.BrandCommentDTO;
import dto.BrandPromoDTO;

public interface BrandPromoService {
	public BrandPromoDTO getBrandPromoDetail(String entCrn);
	public int getBrandPromoBookmark(String memId, String entCrn);
	public int deleteBrandPromoBookmark(String memId, String entCrn);
	public int insertBrandPromoBookmark(String memId, String entCrn);
	public double getCommentAvgRate(String entCrn);
	public int insertBrandComment(BrandCommentDTO bdto);
}
