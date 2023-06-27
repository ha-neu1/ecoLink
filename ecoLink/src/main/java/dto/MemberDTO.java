package dto;

public class MemberDTO {
	String memId, memPw, memNick, memEmail, memType, memRegtime, memName;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemNick() {
		return memNick;
	}

	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}

	public String getMemRegtime() {
		return memRegtime;
	}

	public void setMemRegtime(String memRegtime) {
		this.memRegtime = memRegtime;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	@Override
	public String toString() {
		return "MemberDTO [memId=" + memId + ", memPw=" + memPw + ", memNick=" + memNick + ", memEmail=" + memEmail
				+ ", memType=" + memType + ", memRegtime=" + memRegtime + ", memName=" + memName + "]";
	}
	
	
}
