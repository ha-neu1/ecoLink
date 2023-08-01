package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BrandCommentDTO;
import dto.BrandPromoDTO;


@Repository
@Mapper
public interface BrandPromoDAO {
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
}
