package com.kdzr.http.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.kdzr.http.linksite.WeiXinLogin;
import com.kdzr.http.linksite.WeiXinNoLogin;
import com.kdzr.http.mail.LinksiteSendHtmlMail;

public class TaskLinksite {
	@Autowired
	private WeiXinNoLogin noLogin;
	@Autowired
	private WeiXinLogin login;
	@Autowired
	private LinksiteSendHtmlMail sendMail;
	
	//定时扫描点点通产品功能链接
	public void linksiteHealthCheck(){
		
		noLogin.check_links();;//微信游客模式
		login.check_links();//method2();//微信登录模式
		//method3();//PC游客模式
		//method4();//PC登录模式
		
	}
	
	
	//定时发送邮件
	public void linksiteSendMail(){
		
		sendMail.send();
		
	}

}
