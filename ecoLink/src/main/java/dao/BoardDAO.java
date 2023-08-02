package dao;

import dto.BoardDTO;
import dto.BoardCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {

    // 게시물 목록 조회
    List<BoardDTO> getBoardList();

    // 게시물 작성
    void insertBoard(BoardDTO boardDTO);

    // 게시물 상세 조회
    BoardDTO getBoardById(int boardId);

    // 게시물 수정
    void updateBoard(BoardDTO boardDTO);

    // 게시물 삭제
    void deleteBoard(int boardId);

    // 댓글 작성
    void insertBoardCommentWithNewColumns(BoardCommentDTO boardCommentDTO);

    // 댓글 수정
    void updateBoardCommentWithNewColumns(BoardCommentDTO boardCommentDTO);
}
