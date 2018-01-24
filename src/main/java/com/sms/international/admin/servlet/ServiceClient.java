package com.sms.international.admin.servlet;

import com.sms.international.admin.utils.AESTool;
import com.sms.international.admin.utils.PubType;
import com.sms.international.admin.utils.SignatureUtil;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceClient {

	private static Logger log = Logger.getLogger(ServiceClient.class);
	private static HttpClient client = null;
	@Value("#{configProperties['SERVICE_URL']}")
	private String URL;

	@Value("#{configProperties['SERVICE_URL_92']}")
	private String URL_92;

	private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

	static {
		client = new HttpClient(connectionManager);
		client.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(20);
		client.getHttpConnectionManager().getParams().setMaxTotalConnections(48);
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		client.getHttpConnectionManager().getParams().setSoTimeout(30000);
	}

	public String excuteClient(int type, int method, String data) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("type", type);
		paraMap.put("method", method);
		paraMap.put("data", data);
		//如果请求包含签名、用户信息、用户线路则发送至114
		if(Integer.parseInt(paraMap.get("type")+"") == PubType.USER_SIGN
				||Integer.parseInt(paraMap.get("type")+"") == PubType.USER){
			new Thread(new ServiceClientThread(new ServiceClient(),URL_92,paraMap)).start();
		}
		return postString(URL, paraMap);
	}

	public String postString(String uri, Map<String, Object> paraMap) {
		HttpMethod httpMethod = null;
		String result = null;
		try {

			httpMethod = new PostMethod(StringUtils.isNotBlank(uri) ? uri : URL);
			HttpMethodParams params = new HttpMethodParams();
			params.setContentCharset("UTF-8");

			AESTool aes = new AESTool();
			SignatureUtil signatureUtil = new SignatureUtil();
			String appid = "sioo";
			String token = signatureUtil.findTokenById(appid);
			String key = aes.findKeyById(appid);
			long millis = System.currentTimeMillis();
			StringBuffer buffer = new StringBuffer("{");
			for (String mapkey : paraMap.keySet()) {
				buffer.append("\"").append(mapkey).append("\":").append(paraMap.get(mapkey)).append(",");
			}
			String json = buffer.toString();
			if (json != null && json.length() > 1) {
				json = json.substring(0, json.length() - 1);
			}
			json = json + "}";
			json = aes.encrypt(json, key);
			String lol = signatureUtil.digest(json, "MD5");
			String signature = signatureUtil.generateSignature(appid, token, lol, millis);
			NameValuePair[] param = { new NameValuePair("s", signature), new NameValuePair("a", appid), new NameValuePair("t", String.valueOf(millis)),
					new NameValuePair("l", lol), new NameValuePair("data", json) };
			httpMethod.setQueryString(param);

			int statusCode = client.executeMethod(httpMethod);
			// 执行getMethod
			if (statusCode != HttpStatus.SC_OK) {
				log.error("method failed" + httpMethod.getStatusLine());
				return result;
			}
			// 读取内容
			result = httpMethod.getResponseBodyAsString();
			log.info("response result:" + result);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			httpMethod.releaseConnection();
		}
		return result;
	}

}
