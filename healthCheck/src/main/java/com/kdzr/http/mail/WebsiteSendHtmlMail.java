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

import com.kdzr.http.beans.OldResultInfo;
import com.kdzr.http.dao.GetData;
import com.kdzr.utils.DateUtils;
import com.kdzr.utils.LoadProperties;

@Component
public class WebsiteSendHtmlMail {
	
	private static Logger logger = Logger.getLogger(WebsiteSendHtmlMail.class);
	
	public void send() {

		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定mail server
		senderImpl.setHost("smtp.ustc-zr.com");
		
		List<OldResultInfo> rsList = null;
		//拼凑返回的html页面代码
		StringBuffer sb = new StringBuffer();
    	//sb.append("<html><head></head><body><h1 align=\"center\">链接今日拨测结果如下表：</h1><br><table align=\"center\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\"><tr bgcolor=\"#33cc00\"><th>链接名称</th><th>链接地址</th><th>响应结果</th><th>创建时间</th></tr>");
    	sb.append("<html><head><style type=\"text/css\">span {font-family:'微软雅黑';color:white;font-weight:bolder;font-size: 24px;}#trHead{font-weight: bolder;}.succ {font-weight: bolder;color:#00B050;}.err {font-weight: bolder;color:red;}</style></head><body><table align=\"center\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr bgcolor=\"#808080\"><td colspan=\"4\" align=\"center\"><span>科大智睿网站运行状态报告-" + DateUtils.getFormatDate3(new Date()) + "</span></td></tr><tr id=\"trHead\"><td width=\"25%\">网站名称</td><td width=\"50%\">链接地址</td><td>响应结果</td><td>创建时间</td></tr>");

    	MimeMessage mailMessage = senderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,"GBK");//设置gbk，否则邮件正文中的中文乱码。设置utf-8的话，有些客户端有问题。
		
		String[] array = LoadProperties.getMailsAddr("websiteEmail.properties");//读取收件人配置文件到string数组中，这里用的是测试中代码，要改下。
		
		// 设置收件人，寄件人 用数组发送多个邮件
//		String[] array = new String[] { "junyouren@126.com", "2928407685@qq.com" };
		try {
			messageHelper.setTo(array);
			// mailMessage.setTo("2928407685@qq.com");
			messageHelper.setFrom("rjy@ustc-zr.com");
			messageHelper.setSubject(DateUtils.getFormatDate2(new Date()) +  "-自动巡检报告");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		rsList = GetData.getOldResultInfo(new Date());//结果links表中获取数据
        
        for(int i=0;i<rsList.size();i++){
        	
        	if("成功".equals(rsList.get(i).getResult())){
        		if(i != rsList.size()-1){
                	sb.append("<tr><td>" + rsList.get(i).getLinkName() + "</td><td>" + rsList.get(i).getLinkUrl() + "</td><td class=\"succ\">成功</td><td>" + DateUtils.formatTimeSta(rsList.get(i).getCreateTime()) + "</td></tr>");

            	}else{
                	sb.append("<tr><td>" + rsList.get(i).getLinkName() + "</td><td>" + rsList.get(i).getLinkUrl() + "</td><td class=\"succ\">成功</td><td>" + DateUtils.formatTimeSta(rsList.get(i).getCreateTime()) + "</td></tr></tbody></table></body></html>");
                	break;
            	}
        	}else{
        		if(i != rsList.size()-1){
                	sb.append("<tr><td>" + rsList.get(i).getLinkName() + "</td><td>" + rsList.get(i).getLinkUrl() + "</td><td class=\"err\">失败</td><td>" + DateUtils.formatTimeSta(rsList.get(i).getCreateTime()) + "</td></tr>");

            	}else{
                	sb.append("<tr><td>" + rsList.get(i).getLinkName() + "</td><td>" + rsList.get(i).getLinkUrl() + "</td><td class=\"err\">失败</td><td>" + DateUtils.formatTimeSta(rsList.get(i).getCreateTime()) + "</td></tr></tbody></table></body></html>");
                	break;
            	}
        	}
        	
        }
        
        if(rsList.size() == 0){
        	sb.append("</table></body></html>");
        }
		
		logger.info("拼凑返回的html页面代码:" + sb.toString());
		
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
