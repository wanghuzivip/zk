package com.cy.ssm.view;
import java.util.List;
public class BackPicView {
	private String id;
	private long userId;
	private List<BackPicItemView> backPics;
	private long version;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public List<BackPicItemView> getBackPics() {
		return backPics;
	}
	public void setBackPics(List<BackPicItemView> backPics) {
		this.backPics = backPics;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
}
