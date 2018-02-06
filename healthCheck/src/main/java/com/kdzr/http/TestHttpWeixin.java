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
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class TestHttpWeixin {

	// 创建CookieStore实例
	static CookieStore cookieStore = null;
	static HttpClientContext context = null;
	String loginUrl = "http://campus.kdzrgroup.com/BaseModule//m-Mobile/Login/BindUser?TenantID=7&returnUrl=http://campus.kdzrgroup.com/BaseModule/m-Mobile/Member/Center?TenantID=7";
	String testUrl = "http://campus.kdzrgroup.com/CanteenService/Canteen/CanteenIndex?TenantID=7";

	@Test
	public void testLogin() throws Exception {

		System.out.println("----testLogin");
		// 直接创建client
		CloseableHttpClient client = HttpClients.createDefault();

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
		UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(parameterMap), "UTF-8");
		httpPost.setEntity(postEntity);// httpPost绑定post请求参数
		System.out.println("request line:" + httpPost.getRequestLine());
		try {
			// 执行post请求
			HttpResponse httpResponse = client.execute(httpPost);
			/*
			 * String location =
			 * httpResponse.getFirstHeader("Location").getValue();
			 * System.out.println(location + ">>>>>"); if (location != null) {
			 * System.out.println("----loginError"); }
			 */
			printResponse(httpResponse);

			// 执行保修单查询请求 post
			/*
			 * System.out.println("----the same client"); HttpPost httpPost2 =
			 * new HttpPost(loginUrl);
			 * 
			 * Map<String, String> parameterMap2 = new HashMap<String,
			 * String>(); parameterMap.put("startPage", "1");
			 * parameterMap.put("keyword", ""); parameterMap.put("type", "");
			 * parameterMap.put("status", "21,23"); parameterMap.put("order",
			 * ""); UrlEncodedFormEntity postEntity2 = new
			 * UrlEncodedFormEntity(getParam(parameterMap2), "UTF-8");
			 * httpPost.setEntity(postEntity2);// httpPost绑定post请求参数
			 * 
			 * 
			 * System.out.println("request line:" + httpPost2.getRequestLine());
			 * HttpResponse httpResponse1 = client.execute(httpPost2);
			 * printResponse(httpResponse1);
			 */

			// 执行get请求
//			System.out.println("----the same client");
//			HttpGet httpGet = new HttpGet(testUrl);
//			System.out.println("request line:" + httpGet.getRequestLine());
//			HttpResponse httpResponse1 = client.execute(httpGet);
//			printResponse(httpResponse1);
			
			Document doc = Jsoup.connect(testUrl).get();
			Elements divs = doc.select("div.con-t");//class等于con-t的div标签
			boolean flag = false;
			for (Element div : divs){
				Document divDoc = Jsoup.parse(div.toString());
				Elements name = divDoc.select("b");  //选择器的形式
				if("我的食堂".equals(name.text())){
					flag = true;
					break;
				}
			}
			if(flag){
				System.out.println("食堂主页面访问正常");
			}else{
				System.out.println("食堂主页面访问异常");
			}
			
			
			

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

	public static void printResponse(HttpResponse httpResponse) throws ParseException, IOException {
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

	public static List<NameValuePair> getParam(Map<String, String> parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> it = parameterMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> parmEntry = (Entry<String, String>) it.next();
			param.add(new BasicNameValuePair((String) parmEntry.getKey(), (String) parmEntry.getValue()));
		}
		return param;
	}

}
