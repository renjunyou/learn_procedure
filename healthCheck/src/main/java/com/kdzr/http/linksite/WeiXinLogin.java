package com.kdzr.http.linksite;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.kdzr.http.beans.Clinks;
import com.kdzr.http.beans.ResultInfo;
import com.kdzr.http.common.Comm_http;
import com.kdzr.http.dao.GetData;
import com.kdzr.http.task.FirstLogin;

@Component
public class WeiXinLogin {
	
	private static Logger logger = Logger.getLogger(WeiXinLogin.class);
	
	public int check_links(){
		//1.先登录，保存好cookie
		FirstLogin login = new FirstLogin();
		login.login();
	
		//2.拨测微信登录后的各种链接
		List<ResultInfo> infoList = new ArrayList<ResultInfo>();
		logger.info("微信登录模式扫描开始：");
		List<Clinks> list1 = GetData.getLinks(3);//拿flag=3的数据
		for(Clinks link : list1){
			//3是 云平台首页  验证规则
			ResultInfo ri = new ResultInfo();
			ri.setLcode(link.getLcode());
			ri.setLinkName(link.getLname());
			ri.setLinkUrl(link.getLurl());
			ri.setCreateTime(new Timestamp(System.currentTimeMillis()));
			ri.setResult(Comm_http.http_get(link.getLurl(),"云平台首页",3));
			infoList.add(ri);
		}
		
		logger.info("B类 flag=3 扫描完毕");
		
		List<Clinks> list2 = GetData.getLinks(4);//拿flag=2的数据
		for(Clinks link : list2){
			//4  动态标题验证规则
			ResultInfo ri = new ResultInfo();
			ri.setLcode(link.getLcode());
			ri.setLinkName(link.getLname());
			ri.setLinkUrl(link.getLurl());
			ri.setCreateTime(new Timestamp(System.currentTimeMillis()));
			ri.setResult(Comm_http.http_get(link.getLurl(),link.getLtitle(),4));
			infoList.add(ri);
		}
		
		logger.info("B类 flag=4 扫描完毕");
		
		//post  flag=5
		List<Clinks> list3 = GetData.getLinks(5);//拿flag=5的数据
		for(Clinks link : list3){
			ResultInfo ri = new ResultInfo();
			ri.setLcode(link.getLcode());
			ri.setLinkName(link.getLname());
			ri.setLinkUrl(link.getLurl());
			ri.setCreateTime(new Timestamp(System.currentTimeMillis()));
			ri.setResult(Comm_http.http_post(link.getLurl()));
			infoList.add(ri);
		}
		
		logger.info("B类 flag=5 扫描完毕");
		
		//System.out.println(infoList);
		int flag = -1;//大于等于0更新数据成功
		flag = GetData.saveData(infoList);// 保存请求结果
		if (flag > 0) {
			logger.info("微信登录模式数据更新成功");
		} else {
			logger.error("微信登录模式数据更新失败");
		}
		
		return flag;
	}
}
