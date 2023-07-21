package dto;

import org.springframework.web.multipart.MultipartFile;

public class BannerDTO {
	MultipartFile file;
	String bannerId, bannerPic, memId;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerPic() {
		return bannerPic;
	}

	public void setBannerPic(String bannerPic) {
		this.bannerPic = bannerPic;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Override
	public String toString() {
		return "BannerDTO [file=" + file + ", bannerId=" + bannerId + ", bannerPic=" + bannerPic + ", memId=" + memId
				+ "]";
	}
	
	

}
