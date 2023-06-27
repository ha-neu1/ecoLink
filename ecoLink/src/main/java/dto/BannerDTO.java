package dto;

public class BannerDTO {
	String bannerId, bannerPic, memId;

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
		return "BannerDTO [bannerId=" + bannerId + ", bannerPic=" + bannerPic + ", memId=" + memId + "]";
	}
	
	

}
