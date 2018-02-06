package com.kdzr.test;

import org.junit.Test;

import com.kdzr.http.linksite.WeiXinLogin;
import com.kdzr.http.linksite.WeiXinNoLogin;

public class TestLinksiteHealthCheck {
	
	@Test
	public void test1(){
		
		WeiXinNoLogin nologin = new WeiXinNoLogin();
		nologin.check_links();
		
		WeiXinLogin login = new WeiXinLogin();
		login.check_links();
		
		
		
	}

}
