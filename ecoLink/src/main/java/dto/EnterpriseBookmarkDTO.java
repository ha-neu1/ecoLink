package dto;

public class EnterpriseBookmarkDTO {
	String memId, entCrn;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getEntCrn() {
		return entCrn;
	}

	public void setEntCrn(String entCrn) {
		this.entCrn = entCrn;
	}

	@Override
	public String toString() {
		return "EnterpriseBookmarkDTO [memId=" + memId + ", entCrn=" + entCrn + "]";
	}
	
	
}
