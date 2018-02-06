package com.kdzr.http.website;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.kdzr.http.beans.OldClinks;
import com.kdzr.http.beans.OldResultInfo;
import com.kdzr.http.common.Comm_http;
import com.kdzr.http.dao.GetData;
import com.kdzr.utils.DateUtils;

@Component
public class WebSiteHealthCheck {

	private static Logger logger = Logger.getLogger(WebSiteHealthCheck.class);

	public int check_webUrls() {

		List<OldResultInfo> infoList = new ArrayList<OldResultInfo>();
		logger.info("监控网址扫描开始：" + DateUtils.getFormatDate(new Date()));
		List<OldClinks> list1 = GetData.getWebsiteLinks();// 拿flag=1的数据
		for (OldClinks link : list1) {
			// 1是点击跳入登录页面的链接
			OldResultInfo ri = new OldResultInfo();
			ri.setLid(link.getLid());
			ri.setLinkName(link.getLname());
			ri.setLinkUrl(link.getLurl());
			ri.setCreateTime(new Timestamp(System.currentTimeMillis()));
			// 发送请求
			int status = Comm_http.http_get(link.getLurl());
			if (status == 0) {
				ri.setResult("成功");
			} else {
				ri.setResult("失败");
			}
			infoList.add(ri);

		}

		logger.info("===============" + list1.size() + "个监控网站链接扫描完毕!==============");

		// System.out.println(infoList);
		int flag = -1;// 大于等于0更新数据成功
		flag = GetData.saveWebsiteData(infoList);// 保存请求结果
		if (flag > 0) {
			logger.info("网站监控扫描数据更新成功");
		} else {
			logger.error("网站监控扫描数据更新失败");
		}

		return flag;

	}

}
