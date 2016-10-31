package com.cy.ssm.beans;

public class Audio {
	private String id;
	private long userId;
	private String audioName;
	private String audioPath;
	private String audioUrl;
	private String audioTextUrl;
	private String audioTextName;
	private String audioTextPath;
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
	public String getAudioName() {
		return audioName;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	public String getAudioPath() {
		return audioPath;
	}
	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public String getAudioTextUrl() {
		return audioTextUrl;
	}
	public void setAudioTextUrl(String audioTextUrl) {
		this.audioTextUrl = audioTextUrl;
	}
	public String getAudioTextName() {
		return audioTextName;
	}
	public void setAudioTextName(String audioTextName) {
		this.audioTextName = audioTextName;
	}
	public String getAudioTextPath() {
		return audioTextPath;
	}
	public void setAudioTextPath(String audioTextPath) {
		this.audioTextPath = audioTextPath;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
