// BoardServiceImpl.java

package service;

import dao.BoardDAO;
import dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    // 이미지를 저장할 경로
    private static final String IMAGE_UPLOAD_DIR = "src/main/resources/static/images/";

    @Override
    public List<BoardDTO> getBoardList() {
        return boardDAO.getBoardList();
    }

    @Override
    public boolean createBoard(BoardDTO boardDTO) {
        // 이미지 업로드 처리
        List<MultipartFile> draggedFiles = boardDTO.getDraggedFiles();
        if (draggedFiles != null && !draggedFiles.isEmpty()) {
            for (MultipartFile file : draggedFiles) {
                try {
                    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get(IMAGE_UPLOAD_DIR + fileName);
                    Files.write(filePath, file.getBytes());

                    // 첫 번째 이미지의 경로를 firstImageUrl로 설정
                    if (boardDTO.getFirstImageUrl() == null) {
                        boardDTO.setFirstImageUrl("/images/" + fileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        int result = boardDAO.createBoard(boardDTO);
        return result > 0;
    }

    @Override
    public BoardDTO getBoardById(int boardId) {
        return boardDAO.getBoardById(boardId);
    }

    @Override
    public boolean updateBoard(BoardDTO boardDTO) {
        int result = boardDAO.updateBoard(boardDTO);
        return result > 0;
    }

    @Override
    public boolean deleteBoard(int boardId) {
        int result = boardDAO.deleteBoard(boardId);
        return result > 0;
    }
}
