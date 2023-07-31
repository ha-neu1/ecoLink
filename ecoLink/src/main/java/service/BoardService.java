package service;

import dto.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    // 게시물 목록 조회
    List<BoardDTO> getBoardList();

    // 게시물 작성
    boolean createBoard(BoardDTO boardDTO, MultipartFile boardImage);

    // 게시물 상세 조회
    BoardDTO getBoardById(int boardId);

    // 게시물 수정
    boolean updateBoard(BoardDTO boardDTO);

    // 게시물 삭제
    boolean deleteBoard(int boardId);
}
