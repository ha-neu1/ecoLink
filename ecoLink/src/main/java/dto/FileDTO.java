package dto;

public class FileDTO {
	String fileIdx, filePath, fileName, fileType;
	int boardId;
	
	public String getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(String fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	@Override
	public String toString() {
		return "FileDTO [fileIdx=" + fileIdx + ", filePath=" + filePath + ", fileName=" + fileName + ", fileType="
				+ fileType + ", boardId=" + boardId + "]";
	}
	
	
}
