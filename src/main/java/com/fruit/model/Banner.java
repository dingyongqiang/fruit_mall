package com.fruit.model;

public class Banner {
    private Integer bannerId;

    private String bannerName;

    private String bannerUrl;

    private String bannerImg;

    private Integer bannerState;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName == null ? null : bannerName.trim();
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg == null ? null : bannerImg.trim();
    }

    public Integer getBannerState() {
        return bannerState;
    }

    public void setBannerState(Integer bannerState) {
        this.bannerState = bannerState;
    }
}