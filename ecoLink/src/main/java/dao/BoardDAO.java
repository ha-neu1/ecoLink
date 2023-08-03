package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.BoardDTO;

@Repository
public class BoardDAO {

    private final SqlSession sqlSession;

    @Autowired
    public BoardDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 게시물 목록 조회
    public List<BoardDTO> getBoardList() {
        return sqlSession.selectList("dao.BoardDAO.getBoardList");
    }

    // 게시물 작성
    public int createBoard(BoardDTO boardDTO) {
        return sqlSession.insert("dao.BoardDAO.createBoard", boardDTO);
    }

    // 게시물 상세 조회
    public BoardDTO getBoardById(int boardId) {
        return sqlSession.selectOne("dao.BoardDAO.getBoardById", boardId);
    }

    // 게시물 수정
    public int updateBoard(BoardDTO boardDTO) {
        return sqlSession.update("dao.BoardDAO.updateBoard", boardDTO);
    }

    // 게시물 삭제
    public int deleteBoard(int boardId) {
        return sqlSession.delete("dao.BoardDAO.deleteBoard", boardId);
    }
}
