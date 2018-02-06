package com.kdzr.http.common;

import java.util.List;

import com.kdzr.http.beans.OldResultInfo;
import com.kdzr.utils.DateUtils;

public class DealData {

	public static String getString(List<OldResultInfo> rsList) {
		
		StringBuffer sb = new StringBuffer();
		
		System.out.println();

		for (int i = 0; i < rsList.size(); i++) {

			if ("成功".equals(rsList.get(i).getResult())) {
				sb.append("<tr><td>" + rsList.get(i).getLinkName() + "</td><td>" + rsList.get(i).getLinkUrl()
						+ "</td><td class=\"succ\">" + rsList.get(i).getResult() + "</td><td>"
						+ DateUtils.formatTimeSta(rsList.get(i).getCreateTime()) + "</td></tr>");
			} else {
				sb.append("<tr><td>" + rsList.get(i).getLinkName() + "</td><td>" + rsList.get(i).getLinkUrl()
						+ "</td><td class=\"err\">" + rsList.get(i).getResult() + "</td><td>"
						+ DateUtils.formatTimeSta(rsList.get(i).getCreateTime()) + "</td></tr>");
			}

		}
		
		System.out.println(sb.toString());

		return null;

	}

}
