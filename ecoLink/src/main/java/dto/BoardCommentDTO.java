package dto;

public class BoardCommentDTO {
	int bcId, boardId;
	String bcContents, memId, bcRegtime, bcUpdateTime,memNick;
	int bcRef, bcReLevel;
	public int getBcId() {
		return bcId;
	}
	public void setBcId(int bcId) {
		this.bcId = bcId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBcContents() {
		return bcContents;
	}
	public void setBcContents(String bcContents) {
		this.bcContents = bcContents;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getBcRegtime() {
		return bcRegtime;
	}
	public void setBcRegtime(String bcRegtime) {
		this.bcRegtime = bcRegtime;
	}
	public String getBcUpdateTime() {
		return bcUpdateTime;
	}
	public void setBcUpdateTime(String bcUpdateTime) {
		this.bcUpdateTime = bcUpdateTime;
	}
	
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	
	public int getBcRef() {
		return bcRef;
	}
	public void setBcRef(int bcRef) {
		this.bcRef = bcRef;
	}
	public int getBcReLevel() {
		return bcReLevel;
	}
	public void setBcReLevel(int bcReLevel) {
		this.bcReLevel = bcReLevel;
	}
	@Override
	public String toString() {
		return "BoardCommentDTO [bcId=" + bcId + ", boardId=" + boardId + ", bcContents=" + bcContents + ", memId="
				+ memId + ", bcRegtime=" + bcRegtime + ", bcUpdateTime=" + bcUpdateTime + "]";
	}
	
	
}
