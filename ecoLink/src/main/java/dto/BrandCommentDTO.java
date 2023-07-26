package dto;

public class BrandCommentDTO {
	int brcId, brcRate;
	String brcContents, brcRegtime, brcUpdateTime, entCrn, memId, memNick;
	
	public int getBrcId() {
		return brcId;
	}
	public void setBrcId(int brcId) {
		this.brcId = brcId;
	}
	public int getBrcRate() {
		return brcRate;
	}
	public void setBrcRate(int brcRate) {
		this.brcRate = brcRate;
	}
	public String getBrcContents() {
		return brcContents;
	}
	public void setBrcContents(String brcContents) {
		this.brcContents = brcContents;
	}
	public String getBrcRegtime() {
		return brcRegtime;
	}
	public void setBrcRegtime(String brcRegtime) {
		this.brcRegtime = brcRegtime;
	}
	public String getBrcUpdateTime() {
		return brcUpdateTime;
	}
	public void setBrcUpdateTime(String brcUpdateTime) {
		this.brcUpdateTime = brcUpdateTime;
	}
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
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	@Override
	public String toString() {
		return "BrandCommentDTO [brcId=" + brcId + ", brcRate=" + brcRate + ", brcContents=" + brcContents
				+ ", brcRegtime=" + brcRegtime + ", brcUpdateTime=" + brcUpdateTime + ", entCrn=" + entCrn + ", memId="
				+ memId + ", memNick=" + memNick + "]";
	}
	
	

}
