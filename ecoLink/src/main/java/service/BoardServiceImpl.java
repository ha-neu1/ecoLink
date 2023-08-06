package service;

import dao.BoardDAO;
import dto.BoardDTO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {

	private final SqlSession sqlSession;

	@Autowired
	public BoardServiceImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BoardDTO> getAllBoardList() {
		return sqlSession.selectList("dao.BoardDAO.getAllBoardList");
	}

	@Override
	public List<BoardDTO> getShareBoardList() {
	    return sqlSession.selectList("dao.BoardDAO.getShareBoardList");
	}

	@Override
	public List<BoardDTO> getSortedBoardListByRecent() {
		return sqlSession.selectList("dao.BoardDAO.getSortedBoardListByRecent");
		// 최근 날짜 순으로 정렬된 게시물 목록을 가져오는 메서드 구현
	}

	@Override
	public List<BoardDTO> getSortedBoardListByViews() {
		return sqlSession.selectList("dao.BoardDAO.getSortedBoardListByViews");
		// 조회수 순으로 정렬된 게시물 목록을 가져오는 메서드 구현
	}

	@Override
	public List<BoardDTO> searchBoardsByKeyword(String keyword) {
		return sqlSession.selectList("dao.BoardDAO.searchBoardsByKeyword", keyword);
		// 특정 키워드로 게시물을 검색하는 메서드 구현
	}

	@Override
	public boolean createBoard(BoardDTO boardDTO) {
		// 이미지 업로드 처리
		List<MultipartFile> draggedFiles = boardDTO.getDraggedFiles();
		if (draggedFiles != null && !draggedFiles.isEmpty()) {
			for (MultipartFile file : draggedFiles) {
				try {
					String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
					Path filePath = Paths.get("c:/kdt/upload/" + fileName); // 이미지를 upload 폴더에 저장
					Files.write(filePath, file.getBytes());

					// 첫 번째 이미지의 경로를 firstImageUrl로 설정
					if (boardDTO.getFirstImageUrl() == null) {
						boardDTO.setFirstImageUrl("/upload/" + fileName); // 이미지 경로 설정
					}
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		int result = sqlSession.insert("dao.BoardDAO.insertBoard", boardDTO);
		return result > 0;
	}

	@Override
	public BoardDTO getBoardById(int boardId) {
		return sqlSession.selectOne("dao.BoardDAO.getBoardById", boardId);
	}

	@Override
	public boolean updateBoard(BoardDTO boardDTO) {
		int result = sqlSession.update("dao.BoardDAO.updateBoard", boardDTO);
		return result > 0;
	}

	@Override
	public boolean deleteBoard(int boardId) {
		int result = sqlSession.delete("dao.BoardDAO.deleteBoard", boardId);
		return result > 0;
	}
}
