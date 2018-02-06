package com.kdzr.http.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.kdzr.http.beans.ResultInfo;
import com.kdzr.http.dao.GetData;
import com.kdzr.utils.DateUtils;
import com.kdzr.utils.LoadProperties;

@Component
public class LinksiteSendHtmlMail {
	
	private static Logger logger = Logger.getLogger(LinksiteSendHtmlMail.class);
	
	public void send(){
		
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.ustc-zr.com");// 设定mail server
		
		//拼接邮件 报表中的 固定头部
		StringBuffer sb = new StringBuffer();
    	sb.append("<!DOCTYPE html><html><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"><title>高校点点通功能链接健康检测</title><meta name=\"description\" content=\"\"><meta name=\"keywords\" content=\"\"><style type=\"text/css\">span {font-family: '微软雅黑';color: white;font-weight: bolder;font-size: 24px;}#trHead {font-weight: bolder;}.succ {font-weight: bolder;color: #00B050;}.err {font-weight: bolder;color: red;}</style></head><body><table align=\"center\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr bgcolor=\"#808080\"><td colspan=\"6\" align=\"center\"><span>高校点点通功能链接拨测报告-" + DateUtils.getFormatDate3(new Date()) + "</span></td></tr><tr id=\"trHead\"><td width=\"5%\">分类</td><td width=\"5%\">链接编号</td><td width=\"25%\">功能链接名称</td><td width=\"50%\">链接地址</td><td>响应结果</td><td>创建时间</td></tr>");

    	MimeMessage mailMessage = senderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,"GBK");
    	
    	//加载配置文件mailAddr.properties 到String[]数组
		//准备邮件头部内容，发件人、收件人、主题等
    	String[] mails = LoadProperties.getMailsAddr("linksiteEmail.properties");
    	try {
			messageHelper.setTo(mails);
			// mailMessage.setTo("2928407685@qq.com");
			messageHelper.setFrom("rjy@ustc-zr.com");
			messageHelper.setSubject(DateUtils.getFormatDate2(new Date()) +  "-高校点点通产品自动巡检报告");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    	
    	//准备邮件正文信息，从数据库中提出数据并拼接格式
    	Date date = new Date();
    	List<ResultInfo> rsListA = GetData.getResultInfo(date,"A");//结果表中获取A类数据
    	List<ResultInfo> rsListB = GetData.getResultInfo(date,"B");//结果表中获取B类数据
    	
    	//拼接A类
    	for(int i=0;i<rsListA.size();i++){
        	
        	if(rsListA.get(i).getResult() == 0){//0-成功
        		if("A1".equals(rsListA.get(i).getLcode())){
        			sb.append("<tr><td width=\"5%\" rowspan=\""+ rsListA.size() +"\">微信端游客模式</td><td width=\"5%\">A1</td><td>" + rsListA.get(i).getLinkName() + "</td><td>" + rsListA.get(i).getLinkUrl() + "</td><td class=\"succ\">成功</td><td>" + DateUtils.formatTimeSta(rsListA.get(i).getCreateTime()) + "</td></tr>");
        		}else{
        			sb.append("<tr><td width=\"5%\">"+ rsListA.get(i).getLcode() +"</td><td>" + rsListA.get(i).getLinkName() + "</td><td>" + rsListA.get(i).getLinkUrl() + "</td><td class=\"succ\">成功</td><td>" + DateUtils.formatTimeSta(rsListA.get(i).getCreateTime()) + "</td></tr>");
        		}
        	}else{//失败
        		if("A1".equals(rsListA.get(i).getLcode())){
        			sb.append("<tr><td width=\"5%\" rowspan=\""+ rsListA.size() +"\">微信端游客模式</td><td width=\"5%\">A1</td><td>" + rsListA.get(i).getLinkName() + "</td><td>" + rsListA.get(i).getLinkUrl() + "</td><td class=\"err\">失败</td><td>" + DateUtils.formatTimeSta(rsListA.get(i).getCreateTime()) + "</td></tr>");
        		}else{
        			sb.append("<tr><td width=\"5%\">"+ rsListA.get(i).getLcode() +"</td><td>" + rsListA.get(i).getLinkName() + "</td><td>" + rsListA.get(i).getLinkUrl() + "</td><td class=\"err\">失败</td><td>" + DateUtils.formatTimeSta(rsListA.get(i).getCreateTime()) + "</td></tr>");
        		}
        		
        	}	
    	}		
        		
    	//拼接B类
    	for(int i=0;i<rsListB.size();i++){
        	
        	if(rsListB.get(i).getResult() == 0){//0-成功
        		if("B1".equals(rsListB.get(i).getLcode())){
        			sb.append("<tr><td width=\"5%\" rowspan=\""+ rsListB.size() +"\">微信端游客模式</td><td width=\"5%\">A1</td><td>" + rsListB.get(i).getLinkName() + "</td><td>" + rsListB.get(i).getLinkUrl() + "</td><td class=\"succ\">成功</td><td>" + DateUtils.formatTimeSta(rsListB.get(i).getCreateTime()) + "</td></tr>");
        		}else{
        			sb.append("<tr><td width=\"5%\">"+ rsListB.get(i).getLcode() +"</td><td>" + rsListB.get(i).getLinkName() + "</td><td>" + rsListB.get(i).getLinkUrl() + "</td><td class=\"succ\">成功</td><td>" + DateUtils.formatTimeSta(rsListB.get(i).getCreateTime()) + "</td></tr>");
        		}
        	}else{//失败
        		if("B1".equals(rsListB.get(i).getLcode())){
        			sb.append("<tr><td width=\"5%\" rowspan=\""+ rsListB.size() +"\">微信端登录模式</td><td width=\"5%\">B1</td><td>" + rsListB.get(i).getLinkName() + "</td><td>" + rsListB.get(i).getLinkUrl() + "</td><td class=\"err\">失败</td><td>" + DateUtils.formatTimeSta(rsListB.get(i).getCreateTime()) + "</td></tr>");
        		}else{
        			sb.append("<tr><td width=\"5%\">"+ rsListB.get(i).getLcode() +"</td><td>" + rsListB.get(i).getLinkName() + "</td><td>" + rsListB.get(i).getLinkUrl() + "</td><td class=\"err\">失败</td><td>" + DateUtils.formatTimeSta(rsListB.get(i).getCreateTime()) + "</td></tr>");
        		}
        		
        	}	
    	}		
        		
        //拼接table尾部		
    	sb.append("</tbody></table></body></html>");		
		logger.info(sb.toString());
		
		try {
			messageHelper.setText(sb.toString(),true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		senderImpl.setUsername("rjy@ustc-zr.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("mig91H123"); // 根据自己的情况, 设置password

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);
		logger.info(" 邮件发送成功.. ");
		
	}
}
