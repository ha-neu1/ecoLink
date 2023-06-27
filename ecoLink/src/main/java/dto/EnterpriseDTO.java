package dto;

public class EnterpriseDTO {
	String entCrn, entPhone, memId;

	public String getEntCrn() {
		return entCrn;
	}

	public void setEntCrn(String entCrn) {
		this.entCrn = entCrn;
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

	@Override
	public String toString() {
		return "EnterpriseDTO [entCrn=" + entCrn + ", entPhone=" + entPhone + ", memId=" + memId + "]";
	}
	
	
	
}
