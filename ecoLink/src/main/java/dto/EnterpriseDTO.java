package dto;

import org.springframework.web.multipart.MultipartFile;

public class EnterpriseDTO {
	String entCrn, entPhone, memId;
	String entdMainPic, entdShort, entdURL, entdIntro, entdIntroPic,
	entdPic1, entdPic2, entdPic3, entdExplain1, entdExplain2, entdExplain3;
	boolean entdConfirm;
	MultipartFile entdIntroPicImg, entdPic1Img, entdPic2Img, entdPic3Img;

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

	public boolean isEntdConfirm() {
		return entdConfirm;
	}

	public void setEntdConfirm(boolean entdConfirm) {
		this.entdConfirm = entdConfirm;
	}

	public MultipartFile getEntdIntroPicImg() {
		return entdIntroPicImg;
	}

	public void setEntdIntroPicImg(MultipartFile entdIntroPicImg) {
		this.entdIntroPicImg = entdIntroPicImg;
	}

	public MultipartFile getEntdPic1Img() {
		return entdPic1Img;
	}

	public void setEntdPic1Img(MultipartFile entdPic1Img) {
		this.entdPic1Img = entdPic1Img;
	}

	public MultipartFile getEntdPic2Img() {
		return entdPic2Img;
	}

	public void setEntdPic2Img(MultipartFile entdPic2Img) {
		this.entdPic2Img = entdPic2Img;
	}

	public MultipartFile getEntdPic3Img() {
		return entdPic3Img;
	}

	public void setEntdPic3Img(MultipartFile entdPic3Img) {
		this.entdPic3Img = entdPic3Img;
	}

	@Override
	public String toString() {
		return "EnterpriseDTO [entCrn=" + entCrn + ", entPhone=" + entPhone + ", memId=" + memId + ", entdMainPic="
				+ entdMainPic + ", entdShort=" + entdShort + ", entdURL=" + entdURL + ", entdIntro=" + entdIntro
				+ ", entdIntroPic=" + entdIntroPic + ", entdPic1=" + entdPic1 + ", entdPic2=" + entdPic2 + ", entdPic3="
				+ entdPic3 + ", entdExplain1=" + entdExplain1 + ", entdExplain2=" + entdExplain2 + ", entdExplain3="
				+ entdExplain3 + ", entdConfirm=" + entdConfirm + "]";
	}

}
