package com.kdzr.test;

import org.junit.Test;

import com.kdzr.http.mail.LinksiteSendHtmlMail;

public class TestLinksiteSendMail {
	
	@Test
	public void linksiteSendMail(){
		
		LinksiteSendHtmlMail sendMail = new LinksiteSendHtmlMail();
		sendMail.send();
		
		
		
	}

}
