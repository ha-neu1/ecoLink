package dao;

import dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {

	List<BoardDTO> getAllBoardList();

	List<BoardDTO> getSortedBoardListByRecent();

	List<BoardDTO> getSortedBoardListByViews();

	List<BoardDTO> searchBoardsByKeyword(String keyword);

	// 'share' 게시물 목록 조회
	List<BoardDTO> getShareBoardList();

	// 게시물 작성
	int createBoard(BoardDTO boardDTO);

	// 게시물 상세 조회
	BoardDTO getBoardById(int boardId);

	// 게시물 수정
	int updateBoard(BoardDTO boardDTO);

	// 게시물 삭제
	int deleteBoard(int boardId);
	
	// 조회수 증가
	void increaseViewCount(int boardId);
	
	// 조회수 조회
	int getBoardViewCount(int boardId);
}
