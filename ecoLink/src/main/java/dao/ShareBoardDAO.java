package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dto.BoardDTO;
import dto.FileDTO;
@Mapper
public interface ShareBoardDAO {
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
	int deleteBoard(long boardId);

	// 조회수 증가
	void increaseViewCount(int boardId);

	// 조회수 조회
	int getBoardViewCount(int boardId);

	public int insertBoard(BoardDTO boardDTO);

	public int insertFile(FileDTO dto);

	public int getGeneratedBoardId();

	public List<FileDTO> getFilesByBoardId(int boardId);
}
