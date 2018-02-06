package com.kdzr.test;

import org.junit.Test;

import com.kdzr.http.task.TaskLinksite;
import com.kdzr.http.task.FirstLogin;

public class TestFirstLogin {
	
	@Test
	public void test1(){
		
		FirstLogin login = new FirstLogin();
		login.login();
		TaskLinksite ae = new TaskLinksite();
		//ae.Main();
		
		
		
		
	}
	
	

}
