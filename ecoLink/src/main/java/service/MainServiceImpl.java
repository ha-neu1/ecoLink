package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MainDAO;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	MainDAO maindao;
	
	public MainServiceImpl(MainDAO maindao) {
		this.maindao = maindao;
	}

	@Override
	public String getBannerPicByBannerId(String bannerId) {
		return maindao.getBannerPicByBannerId(bannerId);
	}

	@Override
	public int getMemberCount() {
		return maindao.getMemberCount();
	}

	@Override
	public int getEnterCount() {
		return maindao.getEnterCount();
	}
	@Override
	public int getBoardCount() {
		return maindao.getBoardCount();
	}
}
