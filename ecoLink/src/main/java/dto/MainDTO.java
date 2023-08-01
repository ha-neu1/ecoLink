package dto;

public class MainDTO {
	String entCrn, memId, entdMainPic, memNick, entdURL;

	int boardId;
	String boardTitle, filePath;
	int boardViewCount;
	String boardRegtime, boardUpdateTime, boardContents, boardType;
	
	public String getEntCrn() {
		return entCrn;
	}
	
	public void setEntCrn(String entCrn) {
		this.entCrn = entCrn;
	}
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getEntdMainPic() {
		return entdMainPic;
	}

	public void setEntdMainPic(String entdMainPic) {
		this.entdMainPic = entdMainPic;
	}

	public String getMemNick() {
		return memNick;
	}

	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}

	public String getEntdURL() {
		return entdURL;
	}

	public void setEntdURL(String entdURL) {
		this.entdURL = entdURL;
	}
		
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getBoardRegtime() {
		return boardRegtime;
	}

	public void setBoardRegtime(String boardRegtime) {
		this.boardRegtime = boardRegtime;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	@Override
	public String toString() {
		return "MainDTO [memId=" + memId + ", entdMainPic=" + entdMainPic + ", memNick=" + memNick + ", entdURL="
				+ entdURL + ", boardId=" + boardId + ", boardTitle=" + boardTitle + ", filePath=" + filePath
				+ ", boardContents=" + boardContents + ", boardRegtime=" + boardRegtime + ", boardType=" + boardType
				+ "]";
	}

	
}
