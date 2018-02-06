package com.kdzr.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class LoadProperties {

	public static String[] getMailsAddr(String emailAddr) {
		
		String[] arr = null;
		Resource resource = new ClassPathResource(emailAddr);
		try {
			//这种方式修改属性文件，不用重启Tomcat
			//不要发邮件的人可以把他注释掉(最多可以支持发10个人)
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String toto1 = props.getProperty("toto1");
			String toto2 = props.getProperty("toto2");
			String toto3 = props.getProperty("toto3");
			String toto4 = props.getProperty("toto4");
			String toto5 = props.getProperty("toto5");
			String toto6 = props.getProperty("toto6");
			String toto7 = props.getProperty("toto7");
			String toto8 = props.getProperty("toto8");
			String toto9 = props.getProperty("toto9");
			String toto10 = props.getProperty("toto10");
			
			arr = new String[]{toto1,toto2,toto3,toto4,toto5,toto6,toto7,toto8,toto9,toto10};
			
			//去除数组arr中的null值
			List<String> tmp = new ArrayList<String>();
	        for(String str:arr){
	            if(str!=null && str.length()!=0){
	                tmp.add(str);
	            }
	        }
	        arr = tmp.toArray(new String[0]);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return arr;
		
	}
	
	
	

}
