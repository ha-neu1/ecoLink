package dto;

public class AdminDTO {
	String entCrn, memNick, entPhone, memId, memEmail, memRegtime;
	boolean entdConfirm;
	public String getEntCrn() {
		return entCrn;
	}
	public void setEntCrn(String entCrn) {
		this.entCrn = entCrn;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public String getEntPhone() {
		return entPhone;
	}
	public void setEntPhone(String entPhone) {
		this.entPhone = entPhone;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemRegtime() {
		return memRegtime;
	}
	public void setMemRegtime(String memRegtime) {
		this.memRegtime = memRegtime;
	}
	public boolean isEntdConfirm() {
		return entdConfirm;
	}
	public void setEntdConfirm(boolean entdConfirm) {
		this.entdConfirm = entdConfirm;
	}
	@Override
	public String toString() {
		return "AdminDTO [entCrn=" + entCrn + ", memNick=" + memNick + ", entPhone=" + entPhone + ", memId=" + memId
				+ ", memEmail=" + memEmail + ", memRegtime=" + memRegtime + ", entdConfirm=" + entdConfirm + "]";
	}
	
	

}
