package com.renjunyou.ssmdemo.beans;

public class User {
	
	private int id;
	private String username;
	private int age;
	
	//jackson  对象转json格式，实体类中必须存在无参的构造放方法，否则报错。
	public User() {
		super();
	}
	
	
	public User(int id, String username, int age) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age + "]";
	}

}
