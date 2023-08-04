package dto;

public class BrandPromoListDTO {
	String entCrn, memId, entPhone, entdMainPic, entdShort, entdURL, memRegtime, memNick;
	boolean entdConfirm;
	double avgRate;
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
	public String getEntPhone() {
		return entPhone;
	}
	public void setEntPhone(String entPhone) {
		this.entPhone = entPhone;
	}
	public String getEntdMainPic() {
		return entdMainPic;
	}
	public void setEntdMainPic(String entdMainPic) {
		this.entdMainPic = entdMainPic;
	}
	public String getEntdShort() {
		return entdShort;
	}
	public void setEntdShort(String entdShort) {
		this.entdShort = entdShort;
	}
	public String getEntdURL() {
		return entdURL;
	}
	public void setEntdURL(String entdURL) {
		this.entdURL = entdURL;
	}
	public String getMemRegtime() {
		return memRegtime;
	}
	public void setMemRegtime(String memRegtime) {
		this.memRegtime = memRegtime;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public boolean isEntdConfirm() {
		return entdConfirm;
	}
	public void setEntdConfirm(boolean entdConfirm) {
		this.entdConfirm = entdConfirm;
	}
	public double getAvgRate() {
		return avgRate;
	}
	public void setAvgRate(double avgRate) {
		this.avgRate = avgRate;
	}
	@Override
	public String toString() {
		return "BrandPromoListDTO [entCrn=" + entCrn + ", memId=" + memId + ", entPhone=" + entPhone + ", entdMainPic="
				+ entdMainPic + ", entdShort=" + entdShort + ", entdURL=" + entdURL + ", memRegtime=" + memRegtime
				+ ", memNick=" + memNick + ", entdConfirm=" + entdConfirm + ", avgRate=" + avgRate + "]";
	}
	
	
	

}
