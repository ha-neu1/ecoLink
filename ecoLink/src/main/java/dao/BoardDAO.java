package dao;

import dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {

    // 게시물 목록 조회
    List<BoardDTO> getBoardList();

    // 게시물 작성
    int createBoard(BoardDTO boardDTO);

    // 게시물 상세 조회
    BoardDTO getBoardById(int boardId);

    // 게시물 수정
    int updateBoard(BoardDTO boardDTO);

    // 게시물 삭제
    int deleteBoard(int boardId);

    // 'share' 게시물 목록 조회
    List<BoardDTO> getShareBoardList();
}
