package com.cy.ssm.view;

import java.util.List;

public class AudioView {
	private String id;
	private long userId;
	List<AudioItemView> audios;
	private int version;
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
	public List<AudioItemView> getAudios() {
		return audios;
	}
	public void setAudios(List<AudioItemView> audios) {
		this.audios = audios;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
