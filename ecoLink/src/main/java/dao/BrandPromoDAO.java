package dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BrandPromoDTO;


@Repository
@Mapper
public interface BrandPromoDAO {
	public BrandPromoDTO getBrandPromoDetail(String entCrn);
}
