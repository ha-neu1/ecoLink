package dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadDTO {
	MultipartFile file1;
	int boardId;
	
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	@Override
	public String toString() {
		return "UploadDTO [file1=" + file1 + ", boardId=" + boardId + "]";
	}
	
	
	

}
