package com.cy.ssm.beans;

public class Rule {
	private long id;
	private String ruleDbUrl;
	private String ruleDbVersion;
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
	public String getRuleDbVersion() {
		return ruleDbVersion;
	}
	public void setRuleDbVersion(String ruleDbVersion) {
		this.ruleDbVersion = ruleDbVersion;
	}
}
