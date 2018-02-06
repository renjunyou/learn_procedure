package com.kdzr.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class WeiXinLogin2 {
	
	static Map<String,String> cookie = null;//保存登录成功的cookie，全局使用

	CloseableHttpClient client = null;
	String loginUrl = "http://campus.kdzrgroup.com/BaseModule//m-Mobile/Login/BindUser?TenantID=7&returnUrl=http://campus.kdzrgroup.com/BaseModule/m-Mobile/Member/Center?TenantID=7";
	String testUrl = "http://campus.kdzrgroup.com/OnlineRepair/Students.html";//智能报修
	String postUrl = "http://campus.kdzrgroup.com/OnlineRepair/Students/TableAjax";
	
	@Before
	public void login() {

		client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(loginUrl);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("username", "201714");
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
			//printResponse(httpResponse);
			saveCookie(httpResponse);
			System.out.println("==========before login执行了");

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

	@Test
	public void httpGet() {
		
		client = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(testUrl);
		httpGet.addHeader("Cookie", cookie.get("Cookie"));
		try {
			
			/*System.out.println("请求的headers:");
			HeaderIterator iterator = httpGet.headerIterator();
			while (iterator.hasNext()) {
				System.out.println("\t" + iterator.next());
			}*/

			//System.out.println("============================");
			
			HttpResponse httpResponse = client.execute(httpGet);
			printResponse(httpResponse);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void httpPost(){
		client = HttpClients.createDefault();		
		HttpPost httpPost = new HttpPost(postUrl);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("type", "12,13,21,23,31,41,51,52");
		
		try {

			UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(parameterMap), "UTF-8");
			httpPost.setEntity(postEntity);// httpPost绑定post请求参数
			// 执行post请求
			HttpResponse httpResponse = client.execute(httpPost);
			printResponse(httpResponse);

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
	
	public void printResponse(HttpResponse httpResponse) throws ParseException, IOException {
		// 获取响应消息实体
		HttpEntity entity = httpResponse.getEntity();
		// 响应状态
		System.out.println("status:" + httpResponse.getStatusLine());
		System.out.println("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			System.out.println("\t" + iterator.next());
		}
		// 判断响应实体是否为空
		if (entity != null) {
			String responseString = EntityUtils.toString(entity);
			System.out.println("response length:" + responseString.length());
			System.out.println("response content:" + responseString.replace("\r\n", ""));
		}
	}
	
	public void saveCookie(HttpResponse httpResponse) throws ParseException, IOException {
		
		String cookies = httpResponse.getLastHeader("Set-Cookie").getValue();

		cookies = cookies.substring(0, cookies.indexOf(";")+1);
		cookie = new HashMap<String,String>();
		cookie.put("Cookie", "school_=1;Cookie_TenantID=7;"+ cookies);
		
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
