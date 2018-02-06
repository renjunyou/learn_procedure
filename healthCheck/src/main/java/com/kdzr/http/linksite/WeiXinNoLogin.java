package com.kdzr.http.linksite;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.kdzr.http.beans.Clinks;
import com.kdzr.http.beans.ResultInfo;
import com.kdzr.http.common.Comm_http;
import com.kdzr.http.dao.GetData;
import com.kdzr.utils.DateUtils;

@Component
public class WeiXinNoLogin {
	
	private static Logger logger = Logger.getLogger(WeiXinNoLogin.class);
	
	public int check_links(){
		
		List<ResultInfo> infoList = new ArrayList<ResultInfo>();
		logger.info("微信游客模式扫描开始：" + DateUtils.getFormatDate(new Date()));
		List<Clinks> list1 = GetData.getLinks(1);//拿flag=1的数据
		for(Clinks link : list1){
			//1是点击跳入登录页面的链接
			ResultInfo ri = new ResultInfo();
			ri.setLcode(link.getLcode());
			ri.setLinkName(link.getLname());
			ri.setLinkUrl(link.getLurl());
			ri.setCreateTime(new Timestamp(System.currentTimeMillis()));
			ri.setResult(Comm_http.Jsoup_get(link.getLurl(),"提交",1));
			
			infoList.add(ri);
			
		}
		
		logger.info("A类 flag=1扫描完毕：");
		
		List<Clinks> list2 = GetData.getLinks(2);//拿flag=2的数据
		for(Clinks link : list2){
			//2  非跳转到登录页面的链接   --云平台首页--校验规则
			ResultInfo ri = new ResultInfo();
			ri.setLcode(link.getLcode());
			ri.setLinkName(link.getLname());
			ri.setLinkUrl(link.getLurl());
			ri.setCreateTime(new Timestamp(System.currentTimeMillis()));
			ri.setResult(Comm_http.Jsoup_get(link.getLurl(),"云平台首页",2));
			
			infoList.add(ri);
		}
		logger.info("A类 flag=2扫描完毕：");
		//System.out.println(infoList);
		int flag = -1;//大于等于0更新数据成功
		flag = GetData.saveData(infoList);// 保存请求结果
		if (flag > 0) {
			logger.info("微信游客模式数据更新成功");
		} else {
			logger.error("微信游客模式数据更新失败");
		}
		
		return flag;
		
	}
	

}
