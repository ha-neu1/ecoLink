package service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.InfoBoardDAO;
import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardLikeDTO;
import dto.FileDTO;
import jakarta.servlet.ServletContext;

@Service
public class InfoBoardServiceImpl implements InfoBoardService {
	@Autowired
	InfoBoardDAO dao;
	private ServletContext servletContext;

	@Override
	public int getTotalBoard() {
		return dao.getTotalBoard();
	}

	@Override
	public List<BoardDTO> boardListRecent(int[] limit) {
		List<BoardDTO> boardList = dao.boardListRecent(limit);
		setFirstImageUrls(boardList);
		return boardList;
	}

	@Override
	public List<BoardDTO> boardListView(int[] limit) {
		List<BoardDTO> boardList = dao.boardListView(limit);
		setFirstImageUrls(boardList);
		return boardList;
	}

	@Override
	public List<FileDTO> getFilesByBoardId(int boardId) {
		List<FileDTO> files = dao.getFilesByBoardId(boardId);

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

				file.setFilePath(imagePath);
			}
		}

		return files;
	}

	@Override
	public List<BoardDTO> searchList(HashMap map) {
		List<BoardDTO> boardList = dao.searchList(map);
		setFirstImageUrls(boardList);
		return boardList;

	}

	@Override
	public int getSearchBoard(HashMap map) {
		return dao.getSearchBoard(map);
	}

	@Override
	public int insertBoard(BoardDTO dto) {
		int insertCount = dao.insertBoard(dto);
		int boardId = dao.getGeneratedBoardId(); // 보드를 삽입한 후 생성된 boardId 가져오기
		dto.setBoardId(boardId); // 나중에 파일 삽입에 사용할 수 있도록 DTO에 boardId를 설정
		return insertCount;
	}
	
	

	@Override
	public int updateBoard(BoardDTO dto) {
		
		return dao.updateBoard(dto);
	}

	@Override
	public BoardDTO updateViewcountAndGetDetail(int boardId) {
		int updaterows = dao.updateViewcount(boardId);
		return dao.getDetail(boardId);
	}

	@Override
	public int insertFile(FileDTO dto) {
		return dao.insertFile(dto);
	}

	
	

	@Override
	public int getGeneratedBoardId() {
		return dao.getGeneratedBoardId();
	}

	private void setFirstImageUrls(List<BoardDTO> boardList) {
		for (BoardDTO dto : boardList) {
			List<FileDTO> files = dao.getFilesByBoardId(dto.getBoardId());
			if (files != null && !files.isEmpty()) {
				FileDTO firstFile = files.get(0);
				String imagePath = firstFile.getFilePath();
				String imageName = firstFile.getFileName();

				if (imagePath.startsWith("c:/kdt/upload/")) {
					imagePath = imagePath.replaceFirst("c:/kdt/upload/", "/upload/");
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

	private String getOriginalFileName(String fileNameWithUUID) {
		int indexOfOpeningParenthesis = fileNameWithUUID.indexOf("(");
		int indexOfClosingParenthesis = fileNameWithUUID.indexOf(")");
		if (indexOfOpeningParenthesis != -1 && indexOfClosingParenthesis != -1) {
			return fileNameWithUUID.substring(0, indexOfOpeningParenthesis);
		}
		return fileNameWithUUID;
	}

	@Override
	public int insertBoardComment(BoardCommentDTO boarddto) {
		// Insert the comment into the database
		int result = dao.insertBoardComment(boarddto);
		if (result > 0) {
			// Get the count of comments for the associated boardId
			int commentCount = dao.getCommentCountForBoard(boarddto.getBoardId());

			// Set the bcRef value based on the comment count (incremented by 1)
			boarddto.setBcRef(commentCount);

			// Update the bcRef value in the database
			int updateResult = dao.updateBcRef(boarddto.getBcId());
			if (updateResult > 0) {
				System.out.println("bcRef updated successfully");
			} else {
				System.out.println("Failed to update bcRef");
			}
		} else {
			System.out.println("Failed to insert comment");
		}
		return result;
	}

	@Override
	public int insertReplyComment(BoardCommentDTO boarddto) {
		return dao.insertReplyComment(boarddto);
	}

	@Override
	public int updateBcRef(int bcId) {

		return dao.updateBcRef(bcId);
	}

	@Override
	public int getCommentCountForBoard(int boardId) {
		return dao.getCommentCountForBoard(boardId);
	}

	@Override
	public List<BoardCommentDTO> getAllBoardComment(HashMap<String, Object> clistmap) {
		 
		return dao.getAllBoardComment(clistmap);
	}

	
	@Override
	public List<BoardCommentDTO> getAllBoardReply(int boardId) {
		return dao.getAllBoardReply(boardId);
	}

	@Override
	public int insertBoardLike(BoardLikeDTO likedto) {
		return dao.insertBoardLike(likedto);
	}

	@Override
	public boolean hasUserLikedBoard(String memId, int boardId) {
		return dao.hasUserLikedBoard(memId, boardId);
	}

	@Override
	public void deleteBoardLike(String memId, int boardId) {
		dao.deleteBoardLike(memId, boardId);
	}

	@Override
	public int countLike(int boardId) {
		return dao.countLike(boardId);
	}

	@Override
	public void deleteAllBoard(int boardId) {
		dao.deleteComments(boardId);
		dao.deleteLike(boardId);
		dao.deleteFile(boardId);
		dao.deleteBoard(boardId);
	}
	

	@Override
	public void deleteFile(int boardId) {
		dao.deleteFile(boardId);
	}

	@Override
	public void deleteReply(int bcId) {
		dao.deleteReply(bcId);
		
	}

	@Override
	public void deleteComment(int bcId) {
		dao.deleteComment(bcId);
	}

	
	

	
	

	
	
	
	
	
	

}
