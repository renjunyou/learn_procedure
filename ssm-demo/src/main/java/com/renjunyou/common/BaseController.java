package com.renjunyou.common;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	
	/*
	 * 获取请求的url，但去除pc参数
	 */
	protected String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		int fromIndex = url.lastIndexOf("&page=");
		if(fromIndex == -1) return url;
		int toIndex = url.indexOf("&", fromIndex + 1);
		if(toIndex == -1) return url.substring(0, fromIndex);
		return url.substring(0, fromIndex) + url.substring(toIndex);
	}

}
