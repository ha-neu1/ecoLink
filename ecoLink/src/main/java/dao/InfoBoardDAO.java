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
public interface InfoBoardDAO {

	public int getTotalBoard();

	public List<BoardDTO> boardListRecent(int[] limit);

	public List<BoardDTO> boardListView(int[] limit);

	public List<BoardDTO> searchList(HashMap map);

	public int getSearchBoard(HashMap map);

	public int insertBoard(BoardDTO dto);
	
	public int updateBoard(BoardDTO dto);

	public int updateViewcount(int boardId);

	public BoardDTO getDetail(int boardId);

	public int insertFile(FileDTO dto);
	
	public int updateFile(FileDTO dto);

	public int getGeneratedBoardId();

	public List<FileDTO> getFilesByBoardId(int boardId);

	public int insertBoardComment(BoardCommentDTO boarddto);

	public int updateBcRef(int bcId);

	public int getCommentCountForBoard(int boardId);

	public List<BoardCommentDTO> getAllBoardComment(HashMap<String, Object> clistmap);

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
