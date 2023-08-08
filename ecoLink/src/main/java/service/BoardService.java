package service;

import dto.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> getAllBoardList();

    // 'share' 게시물 목록 조회
    List<BoardDTO> getShareBoardList();
    List<BoardDTO> getSortedBoardListByRecent();
    List<BoardDTO> getSortedBoardListByViews();
    List<BoardDTO> searchBoardsByKeyword(String keyword);

    // 게시물 작성
    boolean createBoard(BoardDTO boardDTO);
    int getLastCreatedBoardId();

    // 게시물 상세 조회
    BoardDTO getBoardById(int boardId);

    // 게시물 수정
    BoardDTO getBoardUpdate(int boardId);
    boolean updateBoard(BoardDTO boardDTO);

    // 게시물 삭제
    void deleteBoard(int boardId);

    // 조회수 증가
    void increaseViewCount(int boardId);

    // 조회수 조회
    int getBoardViewCount(int boardId);
}
