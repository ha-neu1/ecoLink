package service;

import dao.BoardDAO;
import dto.BoardDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public boolean createBoard(BoardDTO boardDTO) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<BoardDTO> getBoardList() {
        // TODO: BoardDAO를 사용하여 게시물 목록 조회 로직을 작성합니다.
        return null;
    }

    @Override
    public BoardDTO getBoardById(int boardId) {
        // TODO: BoardDAO를 사용하여 게시물 상세 조회 로직을 작성합니다.
        return null;
    }

    @Override
    public boolean updateBoard(BoardDTO boardDTO) {
        // TODO: BoardDAO를 사용하여 게시물 수정 로직을 작성합니다.
        return false;
    }

    @Override
    public boolean deleteBoard(int boardId) {
        // TODO: BoardDAO를 사용하여 게시물 삭제 로직을 작성합니다.
        return false;
    }
}
