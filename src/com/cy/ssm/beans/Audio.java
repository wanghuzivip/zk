package com.cy.ssm.beans;

public class Audio {
	private String id;
	private long userId;
	private String name;
	private String audioUrl;
	private String audioText;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
