package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ShareBoardDAO2;
import dto.BoardDTO;

@Service
public class ShareBoardServiceImpl2 implements ShareBoardService2 {
	
	@Autowired
	ShareBoardDAO2 dao;

	@Override
	public List<BoardDTO> getNormalBPList(HashMap<String, Object> clistmap) {
		// TODO Auto-generated method stub
		return dao.getNormalBPList(clistmap);
	}

	@Override
	public List<BoardDTO> getviewcountBPList(HashMap<String, Object> clistmap) {
		// TODO Auto-generated method stub
		return dao.getviewcountBPList(clistmap);
	}

	@Override
	public int getBPListCount() {
		return dao.getBPListCount();
	}

	@Override
	public List<BoardDTO> getoptionBPList(HashMap<String, Object> clistmap) {
		// TODO Auto-generated method stub
		return dao.getoptionBPList(clistmap);
	}

	@Override
	public int getoptionBPListCount(String memNick) {
		return dao.getoptionBPListCount(memNick);
	}
	
	

}
