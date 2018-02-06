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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TestHttpPC {

	// 创建CookieStore实例
	static CookieStore cookieStore = null;
	static HttpClientContext context = null;
	String getCodeUrl = "http://adminpc.kdzrgroup.com/BaseModulePC/Login/GetRandomNumberString";
	String loginUrl = "http://adminpc.kdzrgroup.com/BaseModulePC/Login/SignIn";// 登录
	String testUrl = "http://adminpc.kdzrgroup.com/BaseModulePC/";// 登录后的首页面
	String repairUrl = "http://adminpc.kdzrgroup.com/BaseModulePC/Login/BindUser/?returnUrl=http://adminpc.kdzrgroup.com/VenueBookPC/VenueBookPC/Court/GetCourt";

	@Before
	public void testLogin() throws Exception {

		System.out.println("----testLogin");
		// 直接创建client
		CloseableHttpClient client = HttpClients.createDefault();

		// String code = null;//获取验证码
		// HttpPost httpPost2 = new HttpPost(getCodeUrl);
		// Map<String, String> parameterMap2 = new HashMap<String, String>();
		// parameterMap2.put("val", "val");
		//
		// UrlEncodedFormEntity postEntity2 = new
		// UrlEncodedFormEntity(getParam(parameterMap2), "UTF-8");
		// httpPost2.setEntity(postEntity2);// httpPost绑定post请求参数
		//
		// try {
		// // 执行post请求
		// HttpResponse httpResponse2 = client.execute(httpPost2);
		// code = printResponse(httpResponse2);

		HttpPost httpPost = new HttpPost(loginUrl);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("username", "admin7");
		parameterMap.put("password", "96e79218965eb72c92a549dd5a330112");

		UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(parameterMap), "UTF-8");
		httpPost.setEntity(postEntity);// httpPost绑定post请求参数
		// System.out.println("request line:" + httpPost.getRequestLine());
		try {
			// 执行post请求
			HttpResponse httpResponse = client.execute(httpPost);
			// /*
			// * String location =
			// * httpResponse.getFirstHeader("Location").getValue();
			// * System.out.println(location + ">>>>>"); if (location != null) {
			// * System.out.println("----loginError"); }
			// */
			printResponse(httpResponse);

			// 执行保修单查询请求 post
			// System.out.println("----baoxiu the same client");
			// HttpPost httpPost2 = new HttpPost(repairUrl);
			//
			// Map<String, String> parameterMap2 = new HashMap<String,
			// String>(); parameterMap.put("startPage", "1");
			// parameterMap.put("keyword", null);
			// parameterMap.put("type", null);
			// parameterMap.put("status", "21,23");
			// parameterMap.put("order",null);
			// UrlEncodedFormEntity postEntity2 = new
			// UrlEncodedFormEntity(getParam(parameterMap2), "UTF-8");
			// httpPost.setEntity(postEntity2);// httpPost绑定post请求参数

			// System.out.println("request line:" + httpPost2.getRequestLine());
			// HttpResponse httpResponse1 = client.execute(httpPost2);
			// printResponse(httpResponse1);

			// 执行get请求
			System.out.println("----get  the same client");
			HttpGet httpGet = new HttpGet(testUrl);
			System.out.println("request line:" + httpGet.getRequestLine());
			HttpResponse httpResponse3 = client.execute(httpGet);
			printResponse(httpResponse3);

			// cookie store 登录后保存cookie信息
			setCookieStore(httpResponse);

			System.out.println(cookieStore);

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
	public void testCookieStore() throws Exception {
		System.out.println("----testCookieStore");
		// 使用cookieStore方式
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//		HttpGet httpGet = new HttpGet(testUrl);
//		System.out.println("request line:" + httpGet.getRequestLine());

		// 执行保修单查询请求 post
		System.out.println("----baoxiu the same client");
		HttpPost httpPost2 = new HttpPost(repairUrl);
		//
		Map<String, String> parameterMap2 = new HashMap<String, String>();
		parameterMap2.put("startindex", "1");
		parameterMap2.put("keyword", "");
		parameterMap2.put("type", "");
		UrlEncodedFormEntity postEntity2 = new UrlEncodedFormEntity(getParam(parameterMap2), "UTF-8");
		httpPost2.setEntity(postEntity2);// httpPost绑定post请求参数

		System.out.println("request line:" + httpPost2.getRequestLine());
		

		try {
			HttpResponse httpResponse1 = client.execute(httpPost2);
			printResponse(httpResponse1);
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

	public static String printResponse(HttpResponse httpResponse) throws ParseException, IOException {

		String responseString = null;
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
			responseString = EntityUtils.toString(entity);
			System.out.println("response length:" + responseString.length());
			System.out.println("response content:" + responseString.replace("\r\n", ""));
		}

		return responseString;
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

	public static void setCookieStore(HttpResponse httpResponse) {
		System.out.println("----setCookieStore");
		cookieStore = new BasicCookieStore();
		// JSESSIONID
		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
		String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
		System.out.println("JSESSIONID:" + JSESSIONID);
		// 新建一个Cookie
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
		cookie.setVersion(0);
		cookie.setDomain("127.0.0.1");
		cookie.setPath("/CwlProClient");
		// cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
		// cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
		// cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
		// cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
		cookieStore.addCookie(cookie);
	}

}
