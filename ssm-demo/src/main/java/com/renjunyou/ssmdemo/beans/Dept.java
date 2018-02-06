package com.renjunyou.ssmdemo.beans;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.renjunyou.ssmdemo.common.utils.JsonDateFormatFull;

public class Dept {
	
	private int deptId;
	private String deptName;
	//日期类型  jackson  对象--json转化  加上这个注解
	@JsonSerialize(using = JsonDateFormatFull.class)
	private Date createDate;
	
	public Dept() {
		super();
	}
	
	public Dept(int deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + ", createDate=" + createDate + "]";
	}
	
	
	
	

}
