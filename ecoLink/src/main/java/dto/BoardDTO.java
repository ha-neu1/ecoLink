package dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	int boardId;
	String boardTitle;
	int boardViewCount;
	String boardRegtime, boardUpdateTime, boardContents, memId, boardType;
	List<MultipartFile> files; 
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public int getBoardViewCount() {
		return boardViewCount;
	}
	public void setBoardViewCount(int boardViewCount) {
		this.boardViewCount = boardViewCount;
	}
	public String getBoardRegtime() {
		return boardRegtime;
	}
	public void setBoardRegtime(String boardRegtime) {
		this.boardRegtime = boardRegtime;
	}
	public String getBoardUpdateTime() {
		return boardUpdateTime;
	}
	public void setBoardUpdateTime(String boardUpdateTime) {
		this.boardUpdateTime = boardUpdateTime;
	}
	public String getBoardContents() {
		return boardContents;
	}
	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "BoardDTO [boardId=" + boardId + ", boardTitle=" + boardTitle + ", boardViewCount=" + boardViewCount
				+ ", boardRegtime=" + boardRegtime + ", boardUpdateTime=" + boardUpdateTime + ", boardContents="
				+ boardContents + ", memId=" + memId + ", boardType=" + boardType + ", files=" + files + "]";
	}
	
	
	
	
	

}
