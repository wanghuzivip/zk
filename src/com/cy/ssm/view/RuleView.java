package com.cy.ssm.view;

public class RuleView {
	private long id;
	private String name;
	private String ruleDbUrl;
	private int ruleDbVersion;
	private long updateTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRuleDbUrl() {
		return ruleDbUrl;
	}
	public void setRuleDbUrl(String ruleDbUrl) {
		this.ruleDbUrl = ruleDbUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRuleDbVersion() {
		return ruleDbVersion;
	}
	public void setRuleDbVersion(int ruleDbVersion) {
		this.ruleDbVersion = ruleDbVersion;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
}
