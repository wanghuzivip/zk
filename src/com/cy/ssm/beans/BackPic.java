package com.cy.ssm.beans;

public class BackPic {
	private long id;
	private long userId;
	private String backPicInfo;
	private long version;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getBackPicInfo() {
		return backPicInfo;
	}
	public void setBackPicInfo(String backPicInfo) {
		this.backPicInfo = backPicInfo;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
}
