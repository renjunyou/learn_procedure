package com.kdzr.http.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.kdzr.http.mail.WebsiteSendHtmlMail;
import com.kdzr.http.website.WebSiteHealthCheck;

public class TaskWebsite {
	
	@Autowired
	private WebsiteSendHtmlMail sendMail;
	@Autowired
	private WebSiteHealthCheck healthCheck;
	
	
	
	//定时扫描网站链接
	public void websiteHealthCheck(){
		
		healthCheck.check_webUrls();
		
	}
	
	//定时发送邮件
	public void websiteSendMail(){
		
		sendMail.send();
		
	}

}
