package com.fruit.model;

public class Address {
    private Integer addrId;

    private String addrProvince;

    private String addrCity;

    private String addrArea;

    private String addrDetail;

    private Integer addrUser;

    private String addrZipcode;

    private String addrPhone;

    private String addrNickname;

    private Integer addrState;

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public String getAddrProvince() {
        return addrProvince;
    }

    public void setAddrProvince(String addrProvince) {
        this.addrProvince = addrProvince == null ? null : addrProvince.trim();
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity == null ? null : addrCity.trim();
    }

    public String getAddrArea() {
        return addrArea;
    }

    public void setAddrArea(String addrArea) {
        this.addrArea = addrArea == null ? null : addrArea.trim();
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail == null ? null : addrDetail.trim();
    }

    public Integer getAddrUser() {
        return addrUser;
    }

    public void setAddrUser(Integer addrUser) {
        this.addrUser = addrUser;
    }

    public String getAddrZipcode() {
        return addrZipcode;
    }

    public void setAddrZipcode(String addrZipcode) {
        this.addrZipcode = addrZipcode == null ? null : addrZipcode.trim();
    }

    public String getAddrPhone() {
        return addrPhone;
    }

    public void setAddrPhone(String addrPhone) {
        this.addrPhone = addrPhone == null ? null : addrPhone.trim();
    }

    public String getAddrNickname() {
        return addrNickname;
    }

    public void setAddrNickname(String addrNickname) {
        this.addrNickname = addrNickname == null ? null : addrNickname.trim();
    }

    public Integer getAddrState() {
        return addrState;
    }

    public void setAddrState(Integer addrState) {
        this.addrState = addrState;
    }
    public Address(Integer addrId, String addrProvince, String addrCity, String addrArea, String addrDetail,
			Integer addrUser, String addrZipcode, String addrPhone, String addrNickname, Integer addrState) {
		super();
		this.addrId = addrId;
		this.addrProvince = addrProvince;
		this.addrCity = addrCity;
		this.addrArea = addrArea;
		this.addrDetail = addrDetail;
		this.addrUser = addrUser;
		this.addrZipcode = addrZipcode;
		this.addrPhone = addrPhone;
		this.addrNickname = addrNickname;
		this.addrState = addrState;
	}
	public Address() {
		super();
	}
}