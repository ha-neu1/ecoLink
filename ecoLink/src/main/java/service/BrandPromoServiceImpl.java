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

	
	
}
