package com.kdzr.http.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kdzr.http.constant.Constants;

public class Comm_http {

	private static Logger logger = Logger.getLogger(Comm_http.class);

	public static int http_get(String url, String str, int flag) {
		int restatu = -9999;// 返回0表示成功访问到页面
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		httpGet.addHeader("Cookie", Constants.map.get("Cookie"));
		String responseString = null;
		try {
			HttpResponse httpResponse = client.execute(httpGet);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				HttpEntity entity = httpResponse.getEntity();
				// 判断响应实体是否为空
				if (entity != null) {
					try {
						responseString = EntityUtils.toString(entity);
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					Document doc = Jsoup.parse(responseString);

					if (flag == 3) {
						logger.info(url + "========B flag=3类响应内容-->" + doc.toString().replace("\r\n", "").replace("    ", " "));
						Elements divs = doc.select("ul.type_con");// class等于con-t的div标签

						for (Element div : divs) {
							Document divDoc = Jsoup.parse(div.toString());
							Elements name = divDoc.select("li a em"); // 选择器的形式
							for (Element em : name) {
								if (str.equals(em.text())) {
									restatu = 0;
									break;
								}
							}
						}
					} else if (flag == 4) {
						logger.info(url + "========B flag=4类响应内容-->" + doc.toString().replace("\r\n", "").replace("    ", " "));
						Elements divs = doc.select("html head title");// class等于con-t的div标签
						for (Element div : divs) {

							if (str.equals(div.text())) {
								restatu = 0;
								break;
							}
						}
					}
				}else{
					restatu = 1;// 失败-响应实体为空
				}
				
			}else{
				restatu = -1;// 失败-返回状态码错误
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return restatu;

	}

	// 网站链接拨测     响应状态码  200且响应内容长度大于2000  认为是成功
	// -9999 未请求  -1响应状态码错误   0 成功   1 响应实体空  2响应实体长度小于2000  
	public static int http_get(String url) {
		int restatu = -9999;// 返回0表示成功访问到页面
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		String responseString = null;
		try {
			HttpResponse httpResponse = client.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					try {
						responseString = EntityUtils.toString(entity);
						if (responseString.length() > 2000) {
							logger.info(url + "--> response length:" + responseString.length());
							logger.info(url + "--> response content:" + responseString.replace("\r\n", "").replace("    ", " "));
							restatu = 0;// 响应状态码200，且响应长度大于2000 认为是成功的。
						}else{
							restatu = 2;//响应实体内容长度小于2000
						}

					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					restatu = 1;// 失败-响应实体为空
				}

			} else {
				restatu = -1;// 失败-返回状态码错误
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return restatu;
	}

	public static int http_post(String url) {

		int restatu = -9999;// 返回0表示成功跳转登录页面
		CloseableHttpClient client = HttpClients.createDefault();
		Map<String, String> map = null;// 封装post请求的参数
		String afterFix = url.substring(url.indexOf("/", 12) + 1, url.indexOf("/", 28));
		switch (afterFix) {

		case "OnlineRepair":

			map = new HashMap<String, String>();
			map.put("type", "12,13,21,23,31,41,51,52");
			restatu = postCom(client, url, afterFix, map);
			break;

		case "CanteenService":

			map = new HashMap<String, String>();
			String str = url.substring(url.lastIndexOf("/") + 1, url.length());
			if ("GetTodayDish".equals(str)) {
				map.put("id", "661");
			} else {
				map.put("startIndex", "");
				map.put("diningId", "661");
				map.put("TenantID", "7");
			}
			restatu = postCom(client, url, afterFix, map);
			break;

		case "ServiceSupervision":

			map = new HashMap<String, String>();
			map.put("Page", "1");
			map.put("Type", "");
			map.put("Search", "");
			map.put("TenantID", "7");
			restatu = postCom(client, url, afterFix, map);
			break;

		case "VenueBook":

			map = new HashMap<String, String>();
			map.put("startIndex", "0");
			map.put("q", "");
			map.put("court_type", "");
			map.put("TenantID", "7");
			restatu = postCom(client, url, afterFix, map);
			break;

		case "FleaMarket":

			map = new HashMap<String, String>();
			map.put("startIndex", "0");
			map.put("q", "");
			map.put("cateid", "");
			map.put("type", "1");
			map.put("TenantID", "7");
			restatu = postCom(client, url, afterFix, map);
			break;

		case "LostFound":

			map = new HashMap<String, String>();
			map.put("startIndex", "0");
			map.put("q", "");
			map.put("therd_type", "1");
			map.put("small_type", "");
			map.put("TenantID", "7");
			restatu = postCom(client, url, afterFix, map);
			break;

		case "WorkStudy":

			map = new HashMap<String, String>();
			map.put("startIndex", "0");
			map.put("q", "");
			map.put("type", "");
			map.put("TenantID", "7");
			restatu = postCom(client, url, afterFix, map);
			break;

		case "FriendCircle":

			map = new HashMap<String, String>();
			map.put("page", "1");
			map.put("TenantID", "7");
			restatu = postCom(client, url, afterFix, map);
			break;

		}

		return restatu;

	}

	public static int Jsoup_get(String url, String str, int flag) {
		int restatu = -1;// 返回0表示成功跳转登录页面
		try {
			Document doc = Jsoup.connect(url).get();

			if (flag == 1) {
				logger.info(url + "=======A flag=1类响应内容：-->" + doc.toString().replace("\r\n", "").replace("    ", " "));
				Elements divs = doc.select("section.BZnext");// class等于con-t的div标签

				for (Element div : divs) {
					Document divDoc = Jsoup.parse(div.toString());
					Elements name = divDoc.select("section input"); // 选择器的形式
					if (str.equals(name.attr("value"))) {
						restatu = 0;
						break;
					}
				}
			} else if (flag == 2) {
				logger.info(url + "=======A flag=2类响应内容：-->" + doc.toString().replace("\r\n", "").replace("    ", " "));
				Elements divs = doc.select("ul.type_con");// class等于con-t的div标签

				for (Element div : divs) {
					Document divDoc = Jsoup.parse(div.toString());
					Elements name = divDoc.select("li a em"); // 选择器的形式
					for (Element em : name) {
						if (str.equals(em.text())) {
							restatu = 0;
							break;
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return restatu;

	}

	private static int postCom(HttpClient client, String url, String subUrl, Map<String, String> parameterMap) {

		HttpResponse httpResponse = null;
		String responseString = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Cookie", Constants.map.get("Cookie"));

		try {
			UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(HttpUtils.getParam(parameterMap), "UTF-8");
			httpPost.setEntity(postEntity);// httpPost绑定post请求参数
			httpResponse = client.execute(httpPost);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			// System.out.println("status:" + httpResponse.getStatusLine());
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				return -1;// 返回码错误
			} else {
				if (entity != null) {
					try {
						responseString = EntityUtils.toString(entity);
						if (responseString.length() > 1000) {
							logger.info(subUrl + "--> response length:" + responseString.length());
							logger.info(subUrl + "--> response content:" + responseString.replace("\r\n", "").replace("    ", " "));
							return 0;// 响应状态码200，且响应长度大于1000 认为是成功的。
						}else{
							return 2;//响应实体长度小于1000
						}

					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					return 1;// 响应体为空
				}
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return -2;// 其他错误
	}

}
