package dto;

public class BoardLikeDTO {
	String memId;
	int boardId;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	@Override
	public String toString() {
		return "BoardLikeDTO [memId=" + memId + ", boardId=" + boardId + "]";
	}
	
	
	
}
