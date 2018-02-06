package com.kdzr.mvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdzr.http.beans.OldResultInfo;
import com.kdzr.http.beans.ResultInfo;
import com.kdzr.http.dao.GetData;
import com.kdzr.http.linksite.WeiXinLogin;
import com.kdzr.http.linksite.WeiXinNoLogin;
import com.kdzr.http.mail.HandFixTimesWebEmail;
import com.kdzr.http.website.WebSiteHealthCheck;

@Controller
@RequestMapping(value="/site")
public class ResultController {
	
	private static Logger logger = Logger.getLogger(ResultController.class);
	@Autowired
	private WeiXinNoLogin nologin;//微信游客模式
	@Autowired
	private WeiXinLogin login;//微信登录模式
	@Autowired
	private WebSiteHealthCheck website;//网站数据拨测
	@Autowired
	private HandFixTimesWebEmail webmail;//手动发送邮件
	
	@RequestMapping("/link")
	public @ResponseBody List<ResultInfo> getLinkData(String startTime,String endTime){
		
		logger.info("-----查询高校点点通链接监控数据------");
		logger.info("startTime-->" + startTime);
		logger.info("endTime-->" + endTime);
		
		List<ResultInfo> rList = GetData.getLinksiteDate(startTime,endTime);
		logger.info(rList);
		return rList;
		
	}
	
	@RequestMapping("/zrweb")
	public @ResponseBody List<OldResultInfo> getWebData(String startTime,String endTime){
		
		logger.info("-----查询网站监控数据------");
		logger.info("startTime-->" + startTime);
		logger.info("endTime-->" + endTime);
		//获取数据
		List<OldResultInfo> rList = GetData.getWebsiteDate(startTime,endTime);
		
		return rList;
	}
	
	//手动拨测
	@RequestMapping("/handlerCheck")
	@ResponseBody
	public int handCheck(String type){//1-网站拨测   2-链接拨测
		
		int resultFlag = -9999;
		
		if("1".equals(type)){
			resultFlag = website.check_webUrls();
		}else if("2".equals(type)){
			resultFlag = nologin.check_links() + login.check_links();
		}
		
		return resultFlag;
	}
	
	//手动补发邮件
	@RequestMapping("/handWebEmail")
	@ResponseBody
	public int handWebEmail(String startTime,String endTime){
		
		int resultFlag = -9999;
		
		resultFlag = webmail.send(startTime, endTime);
		
		return resultFlag;
	}
	

}
