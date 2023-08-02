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
            boardDAO.insertBoard(boardDTO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<BoardDTO> getBoardList() {
        return boardDAO.getBoardList();
    }

    @Override
    public BoardDTO getBoardById(int boardId) {
        return boardDAO.getBoardById(boardId);
    }

    @Override
    public boolean updateBoard(BoardDTO boardDTO) {
        try {
            boardDAO.updateBoard(boardDTO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteBoard(int boardId) {
        try {
            boardDAO.deleteBoard(boardId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
