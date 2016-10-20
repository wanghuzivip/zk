package com.cy.ssm.beans;

public class User {
	private Long id;
	private String name;
	private String registCode;
	private int hasUsed;
	private long createTime;
	private long firstUseTime;
	private long lastSynTime;
	private long lastSetTime;
	private long limitTime;
	private String codeDbName;
	private String codeDbUrl;
	private int codeDbVersion;
	private long codeDbLastUpdateTime;
	private String beizhu;
	private int start;
	private int limit;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistCode() {
		return registCode;
	}
	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}
	public int getHasUsed() {
		return hasUsed;
	}
	public void setHasUsed(int hasUsed) {
		this.hasUsed = hasUsed;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getFirstUseTime() {
		return firstUseTime;
	}
	public void setFirstUseTime(long firstUseTime) {
		this.firstUseTime = firstUseTime;
	}
	public long getLastSynTime() {
		return lastSynTime;
	}
	public void setLastSynTime(long lastSynTime) {
		this.lastSynTime = lastSynTime;
	}
	public long getLastSetTime() {
		return lastSetTime;
	}
	public void setLastSetTime(long lastSetTime) {
		this.lastSetTime = lastSetTime;
	}
	public String getCodeDbName() {
		return codeDbName;
	}
	public void setCodeDbName(String codeDbName) {
		this.codeDbName = codeDbName;
	}
	public String getCodeDbUrl() {
		return codeDbUrl;
	}
	public void setCodeDbUrl(String codeDbUrl) {
		this.codeDbUrl = codeDbUrl;
	}
	public int getCodeDbVersion() {
		return codeDbVersion;
	}
	public void setCodeDbVersion(int codeDbVersion) {
		this.codeDbVersion = codeDbVersion;
	}
	public long getCodeDbLastUpdateTime() {
		return codeDbLastUpdateTime;
	}
	public void setCodeDbLastUpdateTime(long codeDbLastUpdateTime) {
		this.codeDbLastUpdateTime = codeDbLastUpdateTime;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public long getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(long limitTime) {
		this.limitTime = limitTime;
	}
}
