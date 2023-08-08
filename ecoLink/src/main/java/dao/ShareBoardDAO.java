package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardLikeDTO;
import dto.FileDTO;
@Mapper
@Repository
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
	//int updateBoard(BoardDTO boardDTO);

	// 게시물 삭제
	int deleteBoard(long boardId);

	// 조회수 증가
	void increaseViewCount(int boardId);

	// 조회수 조회
	int getBoardViewCount(int boardId);
    //여기부터
	public int insertBoard(BoardDTO dto);
	
	public int updateBoard(BoardDTO dto);

	public int updateViewcount(int boardId);

	public BoardDTO getDetail(int boardId);

	public int insertFile(FileDTO dto);
	
	public int getGeneratedBoardId();

	public List<FileDTO> getFilesByBoardId(int boardId);

	public int insertBoardComment(BoardCommentDTO boarddto);

	public int updateBcRef(int bcId);

	public int getCommentCountForBoard(int boardId);

	public List<BoardCommentDTO> getAllBoardComment(HashMap<String, Object> clistmap);
	
	public List<BoardCommentDTO> getAllBoardReply(int boardId);

	public int insertReplyComment(BoardCommentDTO boarddto);

	public int insertBoardLike(BoardLikeDTO likedto);

	public boolean hasUserLikedBoard(String memId, int boardId);

	public void deleteBoardLike(String memId, int boardId);

	public int countLike(int boardId);

	public void deleteComments(int boardId);

	public void deleteLike(int boardId);

	public void deleteFile(int boardId);

	public void deleteBoard(int boardId);
	
	public void deleteReply(int bcId);
	
	public void deleteComment(int bcId);
}
	
