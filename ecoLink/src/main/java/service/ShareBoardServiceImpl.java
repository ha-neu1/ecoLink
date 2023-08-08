package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.ShareBoardDAO;
import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardLikeDTO;
import dto.FileDTO;
@Service
public class ShareBoardServiceImpl implements ShareBoardService{
	  private final ShareBoardDAO ShareboardDAO;

	    @Autowired
	    public ShareBoardServiceImpl(ShareBoardDAO ShareboardDAO) {
	        this.ShareboardDAO = ShareboardDAO;
	    }

	    @Override
	    public List<BoardDTO> getAllBoardList() {
	        return ShareboardDAO.getAllBoardList();
	    }

	    @Override
	    public List<BoardDTO> getShareBoardList() {
	        return ShareboardDAO.getShareBoardList();
	    }

	    @Override
	    public List<BoardDTO> getSortedBoardListByRecent() {
	        return ShareboardDAO.getSortedBoardListByRecent();
	    }

	    @Override
	    public List<BoardDTO> getSortedBoardListByViews() {
	        return ShareboardDAO.getSortedBoardListByViews();
	    }

	    @Override
	    public List<BoardDTO> searchBoardsByKeyword(String keyword) {
	        return ShareboardDAO.searchBoardsByKeyword(keyword);
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

	        return ShareboardDAO.createBoard(boardDTO) > 0;
	    }

	    @Override
	    public BoardDTO getBoardById(int boardId) {
	        return ShareboardDAO.getBoardById(boardId);
	    }

	    @Override
	    public BoardDTO getBoardUpdate(int boardId) {
	        return ShareboardDAO.getBoardById(boardId);
	    }

		/*
		 * @Override public boolean updateBoard(BoardDTO boardDTO) { return
		 * ShareboardDAO.updateBoard(boardDTO) > 0; }
		 */
	    @Override
	    public void deleteBoard(Long boardId) {
	        ShareboardDAO.deleteBoard(boardId);
	    }

	    @Override
	    public void increaseViewCount(int boardId) {
	    	ShareboardDAO.increaseViewCount(boardId);
	    }

	    @Override
	    public int getBoardViewCount(int boardId) {
	        return ShareboardDAO.getBoardViewCount(boardId);
	    }

		//여기부터
	    @Override
		public int insertBoard(BoardDTO dto) {
			int insertCount = ShareboardDAO.insertBoard(dto);
			int boardId = ShareboardDAO.getGeneratedBoardId(); // 보드를 삽입한 후 생성된 boardId 가져오기
			dto.setBoardId(boardId); // 나중에 파일 삽입에 사용할 수 있도록 DTO에 boardId를 설정
			return insertCount;
		}
		
		

		@Override
		public int updateBoard(BoardDTO dto) {
			
			return ShareboardDAO.updateBoard(dto);
		}

		@Override
		public BoardDTO updateViewcountAndGetDetail(int boardId) {
			int updaterows = ShareboardDAO.updateViewcount(boardId);
			return ShareboardDAO.getDetail(boardId);
		}

		@Override
		public int insertFile(FileDTO dto) {
			return ShareboardDAO.insertFile(dto);
		}

		@Override
		public int getGeneratedBoardId() {
			return ShareboardDAO.getGeneratedBoardId();
		}

		private void setFirstImageUrls(List<BoardDTO> boardList) {
			for (BoardDTO dto : boardList) {
				List<FileDTO> files = ShareboardDAO.getFilesByBoardId(dto.getBoardId());
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
			int result = ShareboardDAO.insertBoardComment(boarddto);
			if (result > 0) {
				// Get the count of comments for the associated boardId
				int commentCount = ShareboardDAO.getCommentCountForBoard(boarddto.getBoardId());

				// Set the bcRef value based on the comment count (incremented by 1)
				boarddto.setBcRef(commentCount);

				// Update the bcRef value in the database
				int updateResult = ShareboardDAO.updateBcRef(boarddto.getBcId());
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
			return ShareboardDAO.insertReplyComment(boarddto);
		}

		@Override
		public int updateBcRef(int bcId) {

			return ShareboardDAO.updateBcRef(bcId);
		}

		@Override
		public int getCommentCountForBoard(int boardId) {
			return ShareboardDAO.getCommentCountForBoard(boardId);
		}

		@Override
		public List<BoardCommentDTO> getAllBoardComment(HashMap<String, Object> clistmap) {
			 
			return ShareboardDAO.getAllBoardComment(clistmap);
		}

		
		@Override
		public List<BoardCommentDTO> getAllBoardReply(int boardId) {
			return ShareboardDAO.getAllBoardReply(boardId);
		}

		@Override
		public int insertBoardLike(BoardLikeDTO likedto) {
			return ShareboardDAO.insertBoardLike(likedto);
		}

		@Override
		public boolean hasUserLikedBoard(String memId, int boardId) {
			return ShareboardDAO.hasUserLikedBoard(memId, boardId);
		}

		@Override
		public void deleteBoardLike(String memId, int boardId) {
			ShareboardDAO.deleteBoardLike(memId, boardId);
		}

		@Override
		public int countLike(int boardId) {
			return ShareboardDAO.countLike(boardId);
		}

		@Override
		public void deleteAllBoard(int boardId) {
			ShareboardDAO.deleteComments(boardId);
			ShareboardDAO.deleteLike(boardId);
			ShareboardDAO.deleteFile(boardId);
			ShareboardDAO.deleteBoard(boardId);
		}
		

		@Override
		public void deleteFile(int boardId) {
			ShareboardDAO.deleteFile(boardId);
		}

		@Override
		public void deleteReply(int bcId) {
			ShareboardDAO.deleteReply(bcId);
			
		}

		@Override
		public void deleteComment(int bcId) {
			ShareboardDAO.deleteComment(bcId);
		}
		@Override
		public List<FileDTO> getFilesByBoardId(int boardId) {
			List<FileDTO> files = ShareboardDAO.getFilesByBoardId(boardId);

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
