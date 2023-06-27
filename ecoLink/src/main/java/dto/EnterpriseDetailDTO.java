package dto;

public class EnterpriseDetailDTO {
	String entdMainPic, entdShort, entdURL, entdIntro, entdIntroPic,
	entdPic1, entdPic2, entdPic3, entdExplain1, entdExplain2, entdExplain3,
	entCrn;
	boolean entdConfirm;
	
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
	public String getEntdIntro() {
		return entdIntro;
	}
	public void setEntdIntro(String entdIntro) {
		this.entdIntro = entdIntro;
	}
	public String getEntdIntroPic() {
		return entdIntroPic;
	}
	public void setEntdIntroPic(String entdIntroPic) {
		this.entdIntroPic = entdIntroPic;
	}
	public String getEntdPic1() {
		return entdPic1;
	}
	public void setEntdPic1(String entdPic1) {
		this.entdPic1 = entdPic1;
	}
	public String getEntdPic2() {
		return entdPic2;
	}
	public void setEntdPic2(String entdPic2) {
		this.entdPic2 = entdPic2;
	}
	public String getEntdPic3() {
		return entdPic3;
	}
	public void setEntdPic3(String entdPic3) {
		this.entdPic3 = entdPic3;
	}
	public String getEntdExplain1() {
		return entdExplain1;
	}
	public void setEntdExplain1(String entdExplain1) {
		this.entdExplain1 = entdExplain1;
	}
	public String getEntdExplain2() {
		return entdExplain2;
	}
	public void setEntdExplain2(String entdExplain2) {
		this.entdExplain2 = entdExplain2;
	}
	public String getEntdExplain3() {
		return entdExplain3;
	}
	public void setEntdExplain3(String entdExplain3) {
		this.entdExplain3 = entdExplain3;
	}
	public String getEntCrn() {
		return entCrn;
	}
	public void setEntCrn(String entCrn) {
		this.entCrn = entCrn;
	}
	public boolean isEntdConfirm() {
		return entdConfirm;
	}
	public void setEntdConfirm(boolean entdConfirm) {
		this.entdConfirm = entdConfirm;
	}
	
	@Override
	public String toString() {
		return "EnterpriseDetailDTO [entdMainPic=" + entdMainPic + ", entdShort=" + entdShort + ", entdURL=" + entdURL
				+ ", entdIntro=" + entdIntro + ", entdIntroPic=" + entdIntroPic + ", entdPic1=" + entdPic1
				+ ", entdPic2=" + entdPic2 + ", entdPic3=" + entdPic3 + ", entdExplain1=" + entdExplain1
				+ ", entdExplain2=" + entdExplain2 + ", entdExplain3=" + entdExplain3 + ", entCrn=" + entCrn
				+ ", entdConfirm=" + entdConfirm + "]";
	}
	
	

}
