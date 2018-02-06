package com.kdzr.http.beans;

import java.sql.Timestamp;

public class OldResultInfo {
	
	private int resultId;//拨测结果id
	private int Lid;//链接id
	private String linkName;//链接名称
	private String linkUrl;//链接地址
	private String result;//拨测结果
	private Timestamp createTime;//拨测时间
	
	
	
	public int getLid() {
		return Lid;
	}
	public void setLid(int lid) {
		Lid = lid;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "ResultInfo [linkName=" + linkName + ", linkUrl=" + linkUrl + ", result=" + result + ", createTime="
				+ createTime + "]";
	}

}
