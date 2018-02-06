package com.kdzr.http.beans;

public class Clinks {
	
	private int Lid;
	private String Lcode;//链接编号
	private String Lname;
	private String Ltitle;//链接标题
	private String Lurl;
	private int Lflag;//1表示微信游客跳转登录页
	
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
	public int getLflag() {
		return Lflag;
	}
	public void setLflag(int lflag) {
		Lflag = lflag;
	}
	public String getLcode() {
		return Lcode;
	}
	public void setLcode(String lcode) {
		Lcode = lcode;
	}
	public String getLtitle() {
		return Ltitle;
	}
	public void setLtitle(String ltitle) {
		Ltitle = ltitle;
	}
	@Override
	public String toString() {
		return "Clinks [Lid=" + Lid + ", Lcode=" + Lcode + ", Lname=" + Lname + ", Ltitle=" + Ltitle + ", Lurl=" + Lurl
				+ ", Lflag=" + Lflag + "]";
	}
	

}
