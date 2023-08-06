package service;

import dao.BoardDAO;
import dto.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {

    private final SqlSession sqlSession;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<BoardDTO> getBoardList() {
        return sqlSession.selectList("dao.BoardDAO.getBoardList");
    }

    @Override
    public boolean createBoard(BoardDTO boardDTO) {
        // 이미지 업로드 처리
        List<MultipartFile> draggedFiles = boardDTO.getDraggedFiles();
        if (draggedFiles != null && !draggedFiles.isEmpty()) {
            for (MultipartFile file : draggedFiles) {
                try {
                    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get("c:/kdt/upload/" + fileName); // 이미지를 upload 폴더에 저장
                    Files.write(filePath, file.getBytes());

                    // 첫 번째 이미지의 경로를 firstImageUrl로 설정
                    if (boardDTO.getFirstImageUrl() == null) {
                        boardDTO.setFirstImageUrl("/upload/" + fileName); // 이미지 경로 설정
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        int result = sqlSession.insert("dao.BoardDAO.createBoard", boardDTO);
        return result > 0;
    }

    @Override
    public BoardDTO getBoardById(int boardId) {
        return sqlSession.selectOne("dao.BoardDAO.getBoardById", boardId);
    }

    @Override
    public boolean updateBoard(BoardDTO boardDTO) {
        int result = sqlSession.update("dao.BoardDAO.updateBoard", boardDTO);
        return result > 0;
    }

    @Override
    public boolean deleteBoard(int boardId) {
        int result = sqlSession.delete("dao.BoardDAO.deleteBoard", boardId);
        return result > 0;
    }

    @Override
    public List<BoardDTO> getShareBoardList() {
        return sqlSession.selectList("dao.BoardDAO.getShareBoardList");
    }
}
