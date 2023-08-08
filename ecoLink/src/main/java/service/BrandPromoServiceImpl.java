package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BrandPromoDAO;
import dto.BrandCommentDTO;
import dto.BrandPromoDTO;
import dto.BrandPromoListDTO;


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
	public List<BrandCommentDTO> getAllBrandComments(HashMap<String, Object> clistmap) {
		return dao.getAllBrandComments(clistmap);
	}

	@Override
	public int getCommentCount(String entCrn) {
		return dao.getCommentCount(entCrn);
	}

	@Override
	public int deleteBrandComment(String entCrn, String memId) {
		return dao.deleteBrandComment(entCrn, memId);
	}

	@Override
	public int updateBrandComment(BrandCommentDTO bdto) {
		return dao.updateBrandComment(bdto);
	}

	@Override
	public List<BrandPromoListDTO> getNormalBPList(HashMap<String, Object> clistmap) {
		return dao.getNormalBPList(clistmap);
	}
	
	@Override
	public List<BrandPromoListDTO> getrateBPList(HashMap<String, Object> clistmap) {
		return dao.getrateBPList(clistmap);
	}
	
	@Override
	public List<BrandPromoListDTO> getoptionBPList(HashMap<String, Object> clistmap) {
		return dao.getoptionBPList(clistmap);
	}

	@Override
	public int getBPListCount() {
		return dao.getBPListCount();
	}

	@Override
	public int getoptionBPListCount(String memNick) {
		return dao.getoptionBPListCount(memNick);
	}

}
