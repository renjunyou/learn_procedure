package com.kdzr.http.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.kdzr.http.constant.Constants;

public class FirstLogin {

	CloseableHttpClient client = null;
	String loginUrl = "http://campus.kdzrit.com/BaseModule//m-Mobile/Login/BindUser?TenantID=7&returnUrl=http://campus.kdzrit.com/BaseModule/m-Mobile/Member/Center?TenantID=7";

	public void login() {

		client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(loginUrl);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("username", "202011");
		parameterMap.put("password", "96e79218965eb72c92a549dd5a330112");
		parameterMap.put("serviceProvider", "");
		parameterMap.put("openId", "");
		parameterMap.put("headimgurl", "");
		parameterMap.put("appidtype", "");
		parameterMap.put("unionid", "");
		parameterMap.put("tenantID", "7");
		try {

			UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(parameterMap), "UTF-8");
			httpPost.setEntity(postEntity);// httpPost绑定post请求参数
			// 执行post请求
			HttpResponse httpResponse = client.execute(httpPost);
			// printResponse(httpResponse);
			saveCookie(httpResponse);
			//System.out.println("==========before login执行了");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveCookie(HttpResponse httpResponse) throws ParseException, IOException {

		String cookies = httpResponse.getLastHeader("Set-Cookie").getValue();
		cookies = cookies.substring(0, cookies.indexOf(";") + 1);
		
		Constants.map.put("Cookie", "school_=1;Cookie_TenantID=7;" + cookies);

	}
	
	public List<NameValuePair> getParam(Map<String, String> parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> it = parameterMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> parmEntry = (Entry<String, String>) it.next();
			param.add(new BasicNameValuePair((String) parmEntry.getKey(), (String) parmEntry.getValue()));
		}
		return param;
	}

}
