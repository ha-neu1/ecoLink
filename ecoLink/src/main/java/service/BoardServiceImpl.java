package service;

import dao.BoardDAO;
import dto.BoardDTO;
import dto.FileDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
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
    public BoardDTO getBoardUpdate(int boardId) {
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

    @Override
    public void increaseViewCount(int boardId) {
        boardDAO.increaseViewCount(boardId);
    }

    @Override
    public int getBoardViewCount(int boardId) {
        return boardDAO.getBoardViewCount(boardId);
    }

	
	@Override
	public int insertBoard(BoardDTO dto) {
		int insertCount = boardDAO.insertBoard(dto);
		int boardId = boardDAO.getGeneratedBoardId(); // 보드를 삽입한 후 생성된 boardId 가져오기
		dto.setBoardId(boardId); // 나중에 파일 삽입에 사용할 수 있도록 DTO에 boardId를 설정
		return insertCount;
	}
	@Override
	public int insertFile(FileDTO dto) {
		return boardDAO.insertFile(dto);
	}

	@Override
	public int getGeneratedBoardId() {
		return boardDAO.getGeneratedBoardId();
	}

	private void setFirstImageUrls(List<BoardDTO> boardList) {
		for (BoardDTO dto : boardList) {
			List<FileDTO> files = boardDAO.getFilesByBoardId(dto.getBoardId());
			if (files != null && !files.isEmpty()) {
				FileDTO firstFile = files.get(0);
				String imagePath = firstFile.getFilePath();
				String imageName = firstFile.getFileName();

				if (imagePath.startsWith("c:/kdt/upload/")) {
					imagePath = imagePath.replaceFirst("c:/kdt/upload/", "/upload/");
				}
				else if(imagePath.startsWith("/usr/mydir/upload/")) {
					imagePath = imagePath.replaceFirst("/usr/mydir/upload/", "/upload/");
				}

				//// 이미지 이름에서 "(UUID)" 부분을 제거합니다
				int indexOfOpeningParenthesis = imageName.indexOf("(");
				if (indexOfOpeningParenthesis != -1) {
					imageName = imageName.substring(0, indexOfOpeningParenthesis);
				}

				String imageUrl = imagePath + "/";
				dto.setFirstImageUrl(imageUrl);
			} else {
				// 이미지 없을때
				dto.setFirstImageUrl("/upload/noimage.png");
			}
		}
	}

	
	@Override
	public List<FileDTO> getFilesByBoardId(int boardId) {
		List<FileDTO> files = boardDAO.getFilesByBoardId(boardId);

		if (files == null || files.isEmpty()) {
			// 이미지없을때
			FileDTO defaultImage = new FileDTO();
			defaultImage.setFilePath("/upload/noimage.png");
			files = Collections.singletonList(defaultImage);
		} else {

			for (FileDTO file : files) {
				String imagePath = file.getFilePath();

				// "c:/kdt/upload/", "/upload/"
				if (imagePath.startsWith("c:/kdt/upload/")) {
					imagePath = imagePath.replaceFirst("c:/kdt/upload/", "/upload/");
				}
				else if (imagePath.startsWith("/usr/mydir/upload/")) {
					imagePath = imagePath.replaceFirst("/usr/mydir/upload/", "/upload/");
				}

				file.setFilePath(imagePath);
			}
		}

		return files;
	}
	
    
    
}
