package service;

import dao.BoardDAO;
import dto.BoardDTO;

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

    private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<BoardDTO> getAllBoardList() {
        return boardDAO.getAllBoardList();
    }

    @Override
    public List<BoardDTO> getShareBoardList() {
        return boardDAO.getShareBoardList();
    }

    @Override
    public List<BoardDTO> getSortedBoardListByRecent() {
        return boardDAO.getSortedBoardListByRecent();
    }

    @Override
    public List<BoardDTO> getSortedBoardListByViews() {
        return boardDAO.getSortedBoardListByViews();
    }

    @Override
    public List<BoardDTO> searchBoardsByKeyword(String keyword) {
        return boardDAO.searchBoardsByKeyword(keyword);
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

        return boardDAO.createBoard(boardDTO) > 0;
    }

    @Override
    public BoardDTO getBoardById(int boardId) {
        return boardDAO.getBoardById(boardId);
    }

    @Override
    public boolean updateBoard(BoardDTO boardDTO) {
        return boardDAO.updateBoard(boardDTO) > 0;
    }

    @Override
    public boolean deleteBoard(int boardId) {
        return boardDAO.deleteBoard(boardId) > 0;
    }
}
