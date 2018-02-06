package com.kdzr.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class WeiXinLogin3 {

	// 创建CookieStore实例
	static CookieStore cookieStore = null;
	CloseableHttpClient client = null;
	String loginUrl = "http://campus.kdzrgroup.com/BaseModule//m-Mobile/Login/BindUser?TenantID=7&returnUrl=http://campus.kdzrgroup.com/BaseModule/m-Mobile/Member/Center?TenantID=7";
	String testUrl = "http://campus.kdzrgroup.com/CanteenService/Canteen/CanteenIndex?TenantID=7";
	String postUrl = "http://campus.kdzrgroup.com/OnlineRepair/Students/TableAjax";
	
	@Test
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

			// 保存cookie
			setCookieStore(httpResponse);

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

	
	public void httpGet() {
		
		

		client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

		HttpGet httpGet = new HttpGet(testUrl);
		try {
			// 之前get请求，访问微信端食堂主页
			HttpResponse httpResponse = client.execute(httpGet);
			printResponse(httpResponse);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void httpPost(){
		
		System.out.println(cookieStore);
		
		client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		
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

	public List<NameValuePair> getParam(Map<String, String> parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> it = parameterMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> parmEntry = (Entry<String, String>) it.next();
			param.add(new BasicNameValuePair((String) parmEntry.getKey(), (String) parmEntry.getValue()));
		}
		return param;
	}

	public void setCookieStore(HttpResponse httpResponse) {
		cookieStore = new BasicCookieStore();
		// JSESSIONID
//		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
		Header[] headers = httpResponse.getHeaders("Set-Cookie");
		
		String cookie1 = headers[0].getValue();
		String cookie2 = headers[1].getValue();
		
		
		cookie1 = cookie1.substring(0, cookie1.indexOf(";"));
		String expires = cookie2.substring(cookie2.indexOf("expires=")+ 8, cookie2.lastIndexOf(";"));		
		cookie2 = cookie2.substring(0, cookie2.indexOf(";"));
		
	
		
		// 新建一个Cookie
		BasicClientCookie newcookie = new BasicClientCookie("cookie1", cookie1);
		newcookie.setPath("/");
		BasicClientCookie newcookie1 = new BasicClientCookie("cookie2", cookie2);
		newcookie1.setPath("/");
		//cookie.setExpiryDate(expiryDate);
//		cookieStore.addCookie(cookie);
		
		
		
	}

}
