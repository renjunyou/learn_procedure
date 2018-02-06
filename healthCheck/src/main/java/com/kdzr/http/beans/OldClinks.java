package com.kdzr.http.beans;

public class OldClinks {
	
	private int Lid;
	private String Lname;
	private String Lurl;
	private String Lparam;
	
	public int getLid() {
		return Lid;
	}
	public void setLid(int lid) {
		Lid = lid;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public String getLurl() {
		return Lurl;
	}
	public void setLurl(String lurl) {
		Lurl = lurl;
	}
	public String getLparam() {
		return Lparam;
	}
	public void setLparam(String lparam) {
		Lparam = lparam;
	}
	
	@Override
	public String toString() {
		return "Links [Lid=" + Lid + ", Lname=" + Lname + ", Lurl=" + Lurl + ", Lparam=" + Lparam + "]";
	}

}
