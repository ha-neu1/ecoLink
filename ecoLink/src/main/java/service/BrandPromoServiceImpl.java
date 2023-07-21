package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BrandPromoDAO;
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

	
	
}
