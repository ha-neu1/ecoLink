package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BrandPromoDAO;
import dto.BrandCommentDTO;
import dto.BrandPromoDTO;


@Service
public class BrandPromoServiceImpl implements BrandPromoService {
	
	@Autowired
	BrandPromoDAO dao;

	@Override
	public BrandPromoDTO getBrandPromoDetail(String entCrn) {
		return dao.getBrandPromoDetail(entCrn);
	}

	@Override
	public int getBrandPromoBookmark(String memId, String entCrn) {
		return dao.getBrandPromoBookmark(memId, entCrn);
	}

	@Override
	public int deleteBrandPromoBookmark(String memId, String entCrn) {
		return dao.deleteBrandPromoBookmark(memId, entCrn);
	}

	@Override
	public int insertBrandPromoBookmark(String memId, String entCrn) {
		return dao.insertBrandPromoBookmark(memId, entCrn);
	}

	@Override
	public double getCommentAvgRate(String entCrn) {
		return dao.getCommentAvgRate(entCrn);
	}

	@Override
	public int insertBrandComment(BrandCommentDTO bdto) {
		return dao.insertBrandComment(bdto);
	}

	@Override
	public List<BrandCommentDTO> getAllBrandComment(HashMap<String, Object> clistmap) {
		return dao.getAllBrandComment(clistmap);
	}

	@Override
	public int getCommentCount(String entCrn) {
		return dao.getCommentCount(entCrn);
	}

	
	
}
