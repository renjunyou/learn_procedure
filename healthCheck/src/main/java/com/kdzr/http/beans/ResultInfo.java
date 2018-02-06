package com.kdzr.http.beans;

import java.sql.Timestamp;

public class ResultInfo {
	
	private int resultId;//拨测结果id
	private String lcode;//链接编号
	private String linkName;//链接名称
	private String linkUrl;//链接地址
	private int result;//拨测结果
	private Timestamp createTime;//拨测时间
	
	
	
	public String getLcode() {
		return lcode;
	}
	public void setLcode(String lcode) {
		this.lcode = lcode;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ResultInfo [resultId=" + resultId + ", lcode=" + lcode + ", linkName=" + linkName + ", linkUrl="
				+ linkUrl + ", result=" + result + ", createTime=" + createTime + "]";
	}
	

}
