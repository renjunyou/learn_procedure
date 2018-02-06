package com.kdzr.test;

import org.junit.Test;

import com.kdzr.mvc.controller.ResultController;

public class Test12 {
	
	@Test
	public void tset1(){
		
		String url = "http://campus.kdzrgroup.com/OnlineRepair/Students/TableAjax";
		String subFix = url.substring(url.indexOf("/", 12)+1,url.indexOf("/", 28));
		System.out.println(subFix);
//		System.out.println(url.indexOf("/", 12));
		
		String str = url.substring(url.lastIndexOf("/")+1,url.length());
		System.out.println(str);
		
	}
	
	@Test
	public void test2(){
		
		ResultController sc = new ResultController();
		
		
		
	}
	
	
	
	
	

}
