package com.mk.lottery.httpclient;


import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class SimpleClient {
	public static void main(String[] args) throws Exception{
        String url = "http://www.17500.cn/getData/ssq.TXT";
        HttpGet getMethod = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(getMethod);
        Log.i("SimpleClient", "resCode = " + response.getStatusLine().getStatusCode()); //获取响应码
        Log.i("SimpleClient", "result = " + EntityUtils.toString(response.getEntity(), "utf-8"));//获取服务器响应内容
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		try {
//			String url = "http://www.17500.cn/getData/ssq.TXT";
////			String url = "http://www.baidu.com";
//			HttpGet httpGet = new HttpGet(url);
//			CloseableHttpResponse response = httpClient.execute(httpGet);
//			// 获取返回的状态列表
//		    StatusLine statusLine = response.getStatusLine();
//		    System.out.println("StatusLine : " + statusLine);
//		    if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
//		    	try {
//		    		// 获取response entity
//		    		HttpEntity entity = response.getEntity();
//		    		String responseBody = EntityUtils.toString(entity);
////		    		System.out.println(responseBody);
//		    		String[] lines = responseBody.split("\n");
//		    		for(int i = 0 ; i < lines.length; i++){
//		    			System.out.println(lines[i]);
//		    		}
//		    		System.out.println(lines.length);
//		    		System.out.println(entity.getContentLength());
//		    		System.out.println(entity.getContentType().getValue());
//		    		System.out.println(entity.getContentEncoding());
//		    		// 关闭响应流
////		    		EntityUtils.consume(entity);
//		    	} finally{
//		    		response.close();
//		    	}
//		    }
//		} finally{
//			httpClient.close();
//		}

	}
}
