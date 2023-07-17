package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MainDAO {
	public String getBannerPicByBannerId(@Param("bannerId") String bannerId);

	public int getMemberCount();
	public int getEnterCount();
	public int getBoardCount();
}
