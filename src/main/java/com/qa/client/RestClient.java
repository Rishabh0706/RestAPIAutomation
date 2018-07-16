package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.qa.base.TestBase;

public class RestClient extends TestBase{
	
	//1.GET Method
	public void get(String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse httpResponse = httpClient.execute(httpget); //hit the GET url
		
		//a. Status Code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ------> "+statusCode);
		
		//b. Json String
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON ------> "+responseJson);
		
		//c. All Headers
		Header[] headersArray = httpResponse.getAllHeaders();
		
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Header Array ------> "+allHeaders);
		
	}
	
	
	
	
}
