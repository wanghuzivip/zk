package com.cy.ssm.beans;

public class Audio {
	private long id;
	private long userId;
	private String audioUrl;
	private String audioText;
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
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public String getAudioText() {
		return audioText;
	}
	public void setAudioText(String audioText) {
		this.audioText = audioText;
	}
}
